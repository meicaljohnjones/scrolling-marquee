import java.util.Iterator;
import java.util.Arrays;

class MarqueeIterator implements Iterator<String> {
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
	if (isRising) {
	    if (! isSwitchMsgTime()) {
		currentIndent++;
	    } else {
		currentMsg = (currentMsg + 1) % messages.length;
		currentIndent = width - messages[currentMsg].length() - 1;
		isRising = false;
	    }
	} else {
	    if (! isSwitchMsgTime()) {
		currentIndent --;
	    } else {
		currentIndent = 1;
		currentMsg = (currentMsg + 1) % messages.length;
		isRising = true;
	    }
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

public class Marquee {
    public static void main (String args[]) {
	long scrollTime = 2000; //ms
	int width = 40;
	String[] msgs = new String[] {
	    "* Mae hen wlad fy nhadau *",
	    "* Yn annwyl i mi *",
	    "* Gwlad beirdd a chantorion *",
	    "* Enwogion o fri *",
	    "* Ei gwrol rhyfelwyr *",
	    "* Gwladgarwyr tra mad *",
	    "* Tros rhyddid collasant eu gwaed *",
	    "* Gwlad! Gwlad *",
	    "* Pleidiol wyf i'm gwlad *",
	    "* Tra mor yn fur *",
	    "* I'r bur hoff bau *",
	    "* O bydded i'r heniaith barhau *"
	};
	MarqueeIterator m = new MarqueeIterator(msgs, width);
	while (m.hasNext()) {
    	    long before = System.currentTimeMillis();
	    long iterLength = scrollTime / (width - m.getCurrentMsgLength());
	    System.out.print(m.next());
	    try {
		long after = System.currentTimeMillis();
		Thread.sleep(iterLength - after + before);
	    } catch (InterruptedException i) { System.err.println(i); }
	}
    }
}
