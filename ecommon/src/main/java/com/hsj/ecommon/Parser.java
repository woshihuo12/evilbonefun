package com.hsj.ecommon;

import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

public class Parser implements Iterable<ParsedItem> {

    private String source;
    private List<ParsedItem> itemList = new Vector<ParsedItem>();

    public Parser() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void addMember(ParsedItem item) {
        itemList.add(item);
    }

    public boolean checkItems(String[] itemNames) {
        for (String itemName : itemNames) {
            if (getItem(itemName) == null) {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        Iterator<ParsedItem> iter = getItemListIterator();
        while (iter.hasNext()) {
            ParsedItem parsedItem = iter.next();
            parsedItem.clear();
        }
        itemList.clear();
    }

    public String dump() {
        StringBuilder tmpSb = StringBuilderCache.Acquire();

        Iterator<ParsedItem> iter = getItemListIterator();
        while (iter.hasNext()) {
            ParsedItem parsedItem = iter.next();
            tmpSb.append("\n[").append(parsedItem.getName()).append("]\n");
            Iterator<ParsedItemMember> itemMemberIterator = parsedItem.getMemberListIterator();
            while (itemMemberIterator.hasNext()) {
                ParsedItemMember parsedItemMember = itemMemberIterator.next();
                tmpSb.append(parsedItemMember.getName()).append(" = ")
                        .append(parsedItemMember.getValue()).append("\n");
            }
        }
        return StringBuilderCache.GetStringAndRelease(tmpSb);
    }

    public ParsedItem find(String memberName, String memberValue) {
        Iterator<ParsedItem> itemListIterator = getItemListIterator();
        while (itemListIterator.hasNext()) {
            ParsedItem parsedItem = itemListIterator.next();
            ParsedItemMember parsedItemMember = parsedItem.getMember(memberName);
            if (parsedItemMember == null) {
                continue;
            }
            if (parsedItemMember.getValue().toLowerCase().equals(memberValue.toLowerCase())) {
                return parsedItem;
            }
        }
        return null;
    }

    public ParsedItem getItem(String name) {
        Iterator<ParsedItem> iter = getItemListIterator();
        while (iter.hasNext()) {
            ParsedItem parsedItem = iter.next();
            if (parsedItem.getName().toLowerCase().equals(name.toLowerCase())) {
                return parsedItem;
            }
        }
        return null;
    }

    public ParsedItem getItemById(int id) {
        return find("id", String.valueOf(id));
    }

    public int getItemListSize() {
        return itemList.size();
    }

    public void parse(String filename) throws FileNotFoundException {
        setSource(filename);
        File file = new File(filename);
        if (!file.exists()) {
            LoggerFactory.getLogger(Parser.class).info("Parsing error: '" + file.getAbsolutePath()
                    + "' does not exist.");
            throw new FileNotFoundException(file.getAbsolutePath());
        }
        parse(new FileInputStream(file));
    }

    public void parse(InputStream inputStream) {
        try {
            ParsedItem parsedItem = null;

            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder tmpSb = StringBuilderCache.Acquire();
            String line = null;
            int linenr = 0;

            while ((line = input.readLine()) != null) {
                linenr++;
                line = line.trim();
                if (line.startsWith("#") || line.length() == 0) {
                    continue;
                }
                line = line.split("#")[0];
                line = line.trim();
                String[] object = line.split("=");
                if (object.length == 1) {
                    String hobject = object[0];
                    if (hobject.startsWith("[") && hobject.endsWith("]")) {
                        String hobjectName = hobject.substring(1, hobject.length() - 1);
                        hobjectName = hobjectName.trim();
                        if (getItem(hobjectName) != null) {
                            LoggerFactory.getLogger(Parser.class).info(
                                    parseError(linenr, "Object with name \"" + hobjectName
                                            + "\" already exists.", line)
                            );
                            continue;
                        }
                        parsedItem = new ParsedItem(hobjectName);
                        addMember(parsedItem);
                    }
                }

                if (line.contains("=")) {
                    if (parsedItem == null) {
                        LoggerFactory.getLogger(Parser.class).info(
                                parseError(linenr, "Member needs an object.", line)
                        );
                    }

                    String name = object[0].trim();
                    String value = object.length == 2 ? object[1].trim() : null;
                    ParsedItemMember parsedItemMember = new ParsedItemMember(name, value);
                    parsedItem.addMember(parsedItemMember);
                }

                if (object.length < 1 || object.length > 2) {
                    LoggerFactory.getLogger(Parser.class).info(
                            parseError(linenr, "Invalid member syntax.", line)
                    );
                    continue;
                }
            }

            input.close();
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).warn("Exception", e);
        }
    }

    private String parseError(int linenr, String errorMessage, String line) {
        StringBuilder tmpSb = StringBuilderCache.Acquire();
        tmpSb.append("Parsing error line ").append(linenr).append(" in ").append(getSource())
                .append(" (").append(errorMessage).append("):").append("\"").append(line).append("\"");
        return StringBuilderCache.GetStringAndRelease(tmpSb);
    }

    public Iterator<ParsedItem> getItemListIterator() {
        return itemList.iterator();
    }

    @Override
    public Iterator<ParsedItem> iterator() {
        return getItemListIterator();
    }
}
