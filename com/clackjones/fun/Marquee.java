package com.clackjones.fun;

public class Marquee {
    public static void main (String args[]) {
	long scrollTime = 2000; //ms
	int width = 40;
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
		Thread.sleep(iterLength - after + before);
	    } catch (InterruptedException i) { System.err.println(i); }
	}
    }
}
