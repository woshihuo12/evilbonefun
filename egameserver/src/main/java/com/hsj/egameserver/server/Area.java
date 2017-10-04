package com.hsj.egameserver.server;

import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Iterator;

public class Area {

    public static enum Field {
        PVP,
        MOB,
        PLAYER,
    }

    public int mapSizeX = 1280;
    public int mapSizeY = 1280;
    private byte data[];

    public Area() {
        int bits = Field.values().length * mapSizeX * mapSizeY;
        int size = (bits / 8) + (bits % 8 > 0 ? 1 : 0);
        data = new byte[size];
    }

    public void set(int column, int row, Field field, boolean value) {

        int nbit = getPos(column, row, field);
        int nbyte = nbit / 8;
        int noffset = nbit % 8;
        int val = 1 << noffset;
        if (value) {
            data[nbyte] |= val;
        } else {
            data[nbyte] &= ~val;
        }
    }

    private int getPos(int column, int row, Field field) {
        return ((column + (row * mapSizeX)) * Field.values().length) + field.ordinal();
    }

    public boolean get(int x, int y, Field field) {
        int nbit = getPos(x, y, field);
        int nbyte = nbit / 8;
        int noffset = nbit % 8;

        if (nbyte < 0 || nbyte >= data.length)
            return false;

        return (data[nbyte] & (1 << noffset)) != 0;
    }

    public boolean load(InputStream inputStream, Field field) {

        try {
            ImageInputStream iis = ImageIO.createImageInputStream(inputStream);
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
            if (readers.hasNext()) {
                ImageReader reader = readers.next();
                reader.setInput(iis, true);
                BufferedImage image = reader.read(0);

                if (image.getType() != BufferedImage.TYPE_BYTE_BINARY) {
                    throw new Exception("Invalid image type: " + image.getType() + " expecting BufferedImage.TYPE_BYTE_BINARY(" + BufferedImage.TYPE_BYTE_BINARY + ")");
                }

                if (image.getHeight() != mapSizeY || image.getWidth() != mapSizeX) {
                    throw new Exception("Invalid image size: " + image.getWidth() + "/" + image.getHeight() + " expected: " + mapSizeX + "/" + mapSizeY);
                }
                for (int row = 0; row < image.getHeight(); row++) {
                    for (int column = 0; column < image.getWidth(); column++) {
                        this.set(column, row, field, (byte) image.getRGB(column, row) != 0);
                    }
                }
            } else {
                throw new Exception("No valid reader found");
            }
            //LoggerFactory.getLogger(Area.class).info("done loading: " + file);

            return true;

        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("Exception", e);
            return false;
        }
    }
}
