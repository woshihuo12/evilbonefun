package com.hsj.egameserver.server;

import com.hsj.ecommon.StringBuilderCache;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

import java.nio.channels.SocketChannel;

@Configurable
public class Command {

    private World world;

    public Command(World parent) {
        super();
        world = parent;
    }

    void sendCharList(Client client) {
        client.sendData(DatabaseUtils.getDinamicInstance().getCharList(client));
        client.setState(Client.State.CHAR_LIST);
    }

    void authClient(Client client) {
        String username = client.getUsername();
        String password = client.getPassword();

        int accountId = DatabaseUtils.getDinamicInstance().auth(username, password);

        //Handling for a client that doesn't want to behave
        if (accountId == -1 && client.getLoginType() != Client.LoginType.PLAY) {
            byte key = 0x03;
            byte[] input = username.getBytes();
            byte[] output = new byte[input.length];
            for (int i = 0; i < input.length; i++) {
                output[i] = (byte) ((byte) (input[i] ^ key) % 256);
            }
            username = new String(output);
            input = password.getBytes();
            output = new byte[input.length];
            for (int i = 0; i < input.length; i++) {
                output[i] = (byte) ((byte) (input[i] ^ key) % 256);
            }
            password = new String(output);

            client.setUsername(username);
            client.setPassword(password);

            accountId = DatabaseUtils.getDinamicInstance().auth(username, password);
        }

        if (accountId == -1) {
            LoggerFactory.getLogger(Command.class).info("用户名或密码无效");
            client.sendPacket(PacketFactory.Type.FAIL, "用户名或密码是无效的");
            client.disconnect();
        } else {
            int adminlevel = DatabaseUtils.getDinamicInstance().authAdmin(username, password);
            StringBuilder tmpSb = StringBuilderCache.Acquire();
            tmpSb.append(client).append(": 账户ID:").append(accountId);
            LoggerFactory.getLogger(Command.class).info(StringBuilderCache.GetStringAndRelease(tmpSb));
            client.setAccountId(accountId);

            java.util.Map<SocketChannel, Client> clients = world.getClients();
            synchronized (clients) {
                for (Client cl : clients.values()) {
                    if (cl.equals(client)) {
                        continue;
                    }
                    if (cl.getAccountId() == accountId) {
                        cl.sendPacket(PacketFactory.Type.MSG, new Object[]{"账号在另一个地方登录，您被迫下线"});
                        cl.disconnect();
                        return;
                    }
                }
            }
            sendCharList(client);
        }
    }
}
