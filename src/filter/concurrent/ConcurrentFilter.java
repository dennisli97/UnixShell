package filter.concurrent;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import filter.Filter;

public abstract class ConcurrentFilter extends Filter implements Runnable{
	
	protected LinkedBlockingQueue<String> inQ;
	protected LinkedBlockingQueue<String> outQ;
	protected Thread currThread;
	protected boolean isInterrupted;
	
	@Override
	public void setPrevFilter(Filter prevFilter) {
		prevFilter.setNextFilter(this);
	}
	
	@Override
	public void setNextFilter(Filter nextFilter) {
		if (nextFilter instanceof ConcurrentFilter){
			ConcurrentFilter sequentialNext = (ConcurrentFilter) nextFilter;
			this.next = sequentialNext;
			sequentialNext.prev = this;
			if (this.outQ == null){
				this. outQ= new LinkedBlockingQueue<String>();
			}
			sequentialNext.inQ = this.outQ;
		} else {
			throw new RuntimeException("Should not attempt to link dissimilar filter types.");
		}
	}
	
	//method to link the thread that holds this filter
	public void setCurrThread(Thread t) {
		currThread = t;
	}
	
	public Filter getNext() {
		return next;
	}
	
	protected ConcurrentFilter getPrev() {
		if (prev != null) {
			return (ConcurrentFilter) prev;
		}
		return null;
	}
	
	public void process(){
		while (!isDone() && !isInterrupted) {// haven't finish the process
			String line= null;
			try {
				if (inQ.peek() != null && !inQ.peek().equals("xxxxthisisapoisonpill123123123hahahaha")) {
					line = inQ.take();
				}
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				isInterrupted = true;
			}
			String processedLine = processLine(line);
			if (processedLine != null){
				try {
					outQ.put(processedLine);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					isInterrupted = true;
				}// add out put for next to process
			}

				// hand;e interrupt while waiting
			// get the element from previous, wait ill it's available
		}	
	}	
	
	@Override
	public boolean isDone() {
		// if the inQ reach the poison pill, indicate this filter is done
		if(inQ.peek() != null && inQ.peek().equals("xxxxthisisapoisonpill123123123hahahaha")) {
			return true;
		}
		return false;// if return null, mean the thread is still running
	}
	
	protected abstract String processLine(String line);
	
}
