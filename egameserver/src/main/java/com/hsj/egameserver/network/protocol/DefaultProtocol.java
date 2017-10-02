package com.hsj.egameserver.network.protocol;

import com.hsj.ecommon.StringBuilderCache;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DefaultProtocol extends Protocol
{
    public DefaultProtocol() {
        super();
    }

    public List<String> decryptServer(byte data[]) {
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte)(data[i] - 15);
        }
        return new LinkedList<>(Arrays.asList(new String(data).split("\n")));
    }

    public String combine(Iterable<?> input){
        StringBuilder sb = StringBuilderCache.Acquire();
        Iterator<?> iter = input.iterator();
        while(iter.hasNext()){
            sb.append(String.valueOf(iter.next()));
            sb.append("\n");
        }
        return StringBuilderCache.GetStringAndRelease(sb);
    }

    public byte[] encryptServer(List<String> packets) {
        String packet = combine(packets);
        byte [] buffer = new byte[packet.length()];
        for (int i = 0; i < packet.length(); i++) {
            buffer[i] = (byte) ((packet.charAt(i) ^ 0xc3) + 0x0f);
        }
        return buffer;
    }
}