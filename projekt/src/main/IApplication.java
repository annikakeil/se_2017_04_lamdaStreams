package main;

import java.util.ArrayList;
import java.util.List;

public interface IApplication {
    void init();
    void printRecords();
    double executeQuery01();
    double executeQuery02();
    List<Record> executeQuery03();
    List<String> executeQuery04();
    List<String> executeQuery05();
    List<String> executeQuery06();
    List<String> executeQuery07();
    long executeQuery08();
    List<String> executeQuery09();
    List<String> executeQuery10();
}