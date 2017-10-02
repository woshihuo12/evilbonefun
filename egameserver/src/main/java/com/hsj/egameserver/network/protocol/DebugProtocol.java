package com.hsj.egameserver.network.protocol;

import com.hsj.ecommon.StringBuilderCache;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DebugProtocol extends Protocol {
    public DebugProtocol() {
        super();
    }

    public List<String> decryptServer(byte data[]) {
        return new LinkedList<String>(Arrays.asList(new String(data).split("\n")));
    }

    public String combine(Iterable<?> input) {
        StringBuilder sb = StringBuilderCache.Acquire();
        Iterator<?> iter = input.iterator();
        while (iter.hasNext()) {
            sb.append(String.valueOf(iter.next()));
            sb.append("\n");
        }
        return StringBuilderCache.GetStringAndRelease(sb);
    }

    public byte[] encryptServer(List<String> packets) {
        String packet = combine(packets);
        return packet.getBytes();
    }
}
