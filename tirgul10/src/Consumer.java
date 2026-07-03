class Consumer implements Runnable {
    private final BoundedQueue queue;

    public Consumer(BoundedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            queue.consume();
            try {
                // דימוי זמן עבודה/עיבוד של האיבר הנצרך
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}