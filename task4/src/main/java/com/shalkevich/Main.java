package com.shalkevich;

import com.shalkevich.service.CalculationService;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        log.info("������� �������� N: ");
        int n = scanner.nextInt();
        log.info("������ ���������� ��� N = {}", n);

        CalculationService calculationService = new CalculationService();
        double result = calculationService.calculateSum(n);

        log.info("����� ��������: {}", result);
    }
}
