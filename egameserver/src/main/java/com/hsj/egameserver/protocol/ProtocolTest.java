package com.hsj.egameserver.protocol;

import com.hsj.ecommon.StringBuilderCache;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;

public class ProtocolTest {

    public ProtocolTest() throws UnknownHostException {
        enc();
        dec();
    }

    public void enc() throws UnknownHostException {

        String ip = "93.90.190.134";
        int port = 4005;
        InetAddress address = InetAddress.getByName(ip);
        int version = 2000;

        String packet = "char\n";

        int magic1 = OtherProtocol.magic(address, 0);
        int magic2 = OtherProtocol.magic(address, 1);
        int magic3 = 4; //laglamia
        int magic4 = magic1 - port - magic3 + version;

        //byte [] data = packet.getBytes();
        byte[] data = new byte[]{0x6b, 0x62, 0x69, 0x5c, 0x04};

        for (int i = 0; i < data.length; i++) {
            //data[i]=(byte)(((magic2 ^ data[i]) - 19) ^ magic4);
            System.out.println((byte) data[i]);
            int step1 = magic2 ^ data[i];

            int step2 = step1 - 19;
            int step3 = step2 ^ magic4;
            data[i] = (byte) step3;
            int rstep3 = data[i] ^ magic4;
            int rstep2 = rstep3 + 19;

            int rstep1 = magic2 ^ rstep2;
            System.out.println((byte) rstep1);
        }


        for (int i = 0; i < packet.length(); i++) {

            int rstep3 = packet.charAt(i) ^ magic4;

            int rstep2 = rstep3 + 19;

            int rstep1 = magic2 ^ rstep2;

            data[i] = (byte) rstep1;
        }

        System.out.println(getHex(data));


        System.out.println(new String(data));

    }

    public void dec() throws UnknownHostException {

        String ip = "46.4.196.51";
        //String ip = "127.0.0.1";
        int port = 4005;
        int version = 2000;
        byte input = 'l';
        System.out.println("original: " + Integer.toHexString(input) + " " + input);
        InetAddress address = InetAddress.getByName(ip);
        int magic1 = OtherProtocol.magic(address, 0);
        int magic2 = (port - 17) % 131;

        int step1 = input ^ magic2;

        int step2 = step1 - 49;

        int step3 = magic1 ^ step2;

        byte encrypted = (byte) step3;

        System.out.println("encrypted: " + Integer.toHexString((byte) encrypted) + " " + (byte) encrypted);

        //int r = (byte) (magic1 ^ ((input ^ magic2) - 49));

        int rstep3 = magic1 ^ encrypted;
        int rstep2 = rstep3 + 49;
        int rstep1 = rstep2 ^ magic2;
        System.out.println((byte) rstep1);

        byte decrypted = (byte) rstep1;

        System.out.println("decrypted: " + Integer.toHexString(decrypted) + " " + (byte) decrypted);


        String packet = "login\n";
        byte[] result = encrypt1(packet, ip, port);

        OtherProtocol prot = new OtherProtocol(null);
        prot.setAddress(InetAddress.getByName(ip));
        prot.setPort(port);
        prot.setVersion(version);

        packet = prot.decryptServer(result);

        System.out.println(packet);
        System.out.println(getHex(result));

    }

    /**
     * @param args
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException {


        int r = 62 ^ 2000 ^ 9999;
        System.out.println(r);
        int b = r ^ 9999 ^ 2000;
        System.out.println(b);

        new ProtocolTest();


    }


    public byte[] encrypt1(String test, String ip, int port) throws UnknownHostException {

        InetAddress address = InetAddress.getByName(ip);
        int magic1 = OtherProtocol.magic(address, 0);
        int dword_packet_encrypt_007 = OtherProtocol.magic(address, 1);

        byte[] data = test.getBytes();

        int magic2 = (port - 17) % 131;
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (magic1 ^ ((data[i] ^ magic2) - 49));

        }

        return data;
    }

    public byte[] encrypt2(String test) {


        return null;
    }

    public byte[] encrypt3(String test) {


        return null;
    }

    public byte[] encrypt4(String test) {


        return null;
    }

    static final String HEXES = "0123456789abcdef";

    public static String getHex(byte[] raw) {
        if (raw == null) {
            return null;
        }
        final StringBuilder hex = StringBuilderCache.Acquire();
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4))
                    .append(HEXES.charAt((b & 0x0F)));
        }
        return StringBuilderCache.GetStringAndRelease(hex);
    }
}