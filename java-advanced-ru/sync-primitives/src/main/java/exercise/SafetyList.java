package exercise;

class SafetyList {
    // BEGIN
    private int[] array = new int[1];
    private int size;

    public int getSize() {
        return this.size;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return array[index];
    }
    public synchronized void add(int item) {
        if (size >= array.length) {
            int[] oldArray = array;
            array = new int[size * 2];
            System.arraycopy(oldArray, 0, array, 0, oldArray.length);
        }
        array[size++] = item;
    }
    // END
}
