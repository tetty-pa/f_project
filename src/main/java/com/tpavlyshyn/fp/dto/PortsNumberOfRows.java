package com.tpavlyshyn.fp.dto;


import com.tpavlyshyn.fp.entity.Port;

import java.util.List;

public class PortsNumberOfRows {
    List<Port> ports;
    int numberOfRows;

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }
}
