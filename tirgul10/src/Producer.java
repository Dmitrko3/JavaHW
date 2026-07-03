class Producer implements Runnable {
    private final BoundedQueue queue;
    private final int id;

    public Producer(BoundedQueue queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 15; i++) {
            // היצרן מייצר מספר (משולב עם מזהה היצרן)
            int itemToProduce = (id * 100) + i;
            queue.produce(itemToProduce);
            try {
                // דימוי זמן עבודה קצר לייצור
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}