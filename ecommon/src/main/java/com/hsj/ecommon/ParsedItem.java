package com.hsj.ecommon;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class ParsedItem implements Iterable<ParsedItemMember> {

    private String name;
    private List<ParsedItemMember> memberList = new Vector<ParsedItemMember>();

    public ParsedItem() {
    }

    public ParsedItem(String name) {
        setName(name);
    }

    public void addMember(ParsedItemMember member) {
        memberList.add(member);
    }

    public void addMember(String name, String value) {
        memberList.add(new ParsedItemMember(name, value));
    }

    public boolean checkMembers(String[] memberNames) {
        for (String memberName : memberNames) {
            if (getMember(memberName) == null) {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        memberList.clear();
    }

    public ParsedItemMember getMember(String name) {
        Iterator<ParsedItemMember> iter = getMemberListIterator();
        while (iter.hasNext()) {
            ParsedItemMember member = iter.next();
            if (name.toLowerCase().equals(member.getName().toLowerCase())) {
                return member;
            }
        }
        return null;
    }

    public String getMemberValue(String name) {
        ParsedItemMember member = getMember(name);
        if (member == null) {
            return "";
        }
        return member.getValue();
    }

    public Iterator<ParsedItemMember> getMemberListIterator() {
        return memberList.iterator();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Iterator<ParsedItemMember> iterator() {
        return getMemberListIterator();
    }
}
