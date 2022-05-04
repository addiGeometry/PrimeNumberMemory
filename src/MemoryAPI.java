public interface MemoryAPI {
    //connection methods
    public void connect(String address);
    public void open();

    //game interactions
    public void flip(String tiles);
}
