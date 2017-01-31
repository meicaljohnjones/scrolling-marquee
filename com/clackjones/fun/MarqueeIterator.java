package com.clackjones.fun;

import java.util.Iterator;
import java.util.Arrays;

public class MarqueeIterator implements Iterator<String> {
    private String[] messages;
    private int width;
    private int currentIndent = 1;
    private int currentMsg = 0;
    private boolean isRising;

    public MarqueeIterator(String[] messages, int width) {
	this.messages = messages;
	this.width = width;
	this.currentMsg = 0;
	this.isRising = true;
    }

    public String next() {
	if (! isSwitchMsgTime()) {
	    currentIndent = isRising ? currentIndent + 1 : currentIndent - 1;
	} else {
	    currentMsg = (currentMsg + 1) % messages.length;
	    currentIndent = isRising
		? width - messages[currentMsg].length() - 1
		: 1;
	    isRising = !isRising;
	}

	return String.format("\r%s%s%s", createWhitespaceLeft(), messages[currentMsg],
		      createWhitespaceRight());
    }

    private String createWhitespaceRight() {
	int rightSpace = width - messages[currentMsg].length() - currentIndent;
	String x = "";
	for (int i = 0; i < rightSpace; ++i) {
	    x += " ";
	}

	return x;
    }
    
    private String createWhitespaceLeft() {
	String x = "";
	for (int i = 0; i < currentIndent; ++i) {
	    x += " ";
	}

	return x;
    }
	
    public boolean isSwitchMsgTime() {
	return currentIndent == 0 || currentIndent + messages[currentMsg].length()  == width;
    }

    public boolean hasNext() {
	return true;
    }

    public int getCurrentMsgLength() {
	return messages[currentMsg].length();
    }

    public void remove() { }
}
