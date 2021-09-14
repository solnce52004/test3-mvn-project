package dev.example.beans.details;

import dev.example.printer.PrintBeanInterface;

public interface DetailInterface extends PrintBeanInterface {
    String getName();
    void action();
}
