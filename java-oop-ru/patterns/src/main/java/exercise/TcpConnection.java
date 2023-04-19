package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection implements Connection {
    private Connection state;

    public TcpConnection(String ipaddress, int port) {
        this.state = new Disconnected(this);
    }

    public void setState(Connection state) {
        this.state = state;
    }

    @Override
    public String getCurrentState() {
        return state.getCurrentState();
    }

    @Override
    public void connect() {
        state.connect();
    }

    @Override
    public void disconnect() {
        state.disconnect();
    }

    @Override
    public void write(String string) {
        state.write(string);
    }
}
// END
