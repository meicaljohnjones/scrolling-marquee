package com.clackjones.fun;

public class Marquee {
    public static void main (String args[]) {
	long scrollTime = 2000; //ms
	int width = 80;
	String[] msgs = new String[] {
	    "Lorem ipsum dolor sit amet",
	    "consectetur adipiscing elit",
	    "sed do eiusmod tempor",
	    "incididunt ut labore",
	    "et dolore magna aliqua."
	};
	MarqueeIterator m = new MarqueeIterator(msgs, width);
	while (m.hasNext()) {
    	    long before = System.currentTimeMillis();
	    long iterLength = scrollTime / (width - m.getCurrentMsgLength());
	    System.out.print(m.next());
	    try {
		long after = System.currentTimeMillis();
		long difference = iterLength - after + before;
		if (difference > 0) {
			Thread.sleep(difference);
		}
	    } catch (InterruptedException i) { System.err.println(i); }
	}
    }
}
