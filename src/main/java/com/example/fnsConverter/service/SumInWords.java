package com.example.fnsConverter.service;

public class SumInWords {
    private static final String[] UNITS = {
            "", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"
    };

    private static final String[] UNITSFEM = {
            "", "одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"
    };


    private static final String[] TEENS = {
            "", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
            "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    };

    private static final String[] TENS = {
            "", "десять", "двадцать", "тридцать", "сорок", "пятьдесят",
            "шестьдесят", "семьдесят", "восемьдесят", "девяносто"
    };

    private static final String[] HUNDREDS = {
            "", "сто", "двести", "триста", "четыреста", "пятьсот",
            "шестьсот", "семьсот", "восемьсот", "девятьсот"
    };

    private static final String[] THOUSANDS = {
            "", "тысяча", "тысячи", "тысяч"
    };

    private static final String[] MILLIONS = {
            "", "миллион", "миллиона", "миллионов"
    };

    private static final String[] BILLIONS = {
            "", "миллиард", "миллиарда", "миллиардов"
    };

    private static final String[] KOPECKS = {
            "", "копейка", "копейки", "копеек"
    };

    private static final String[] RUBLES ={
            "", "рубль", "рубля", "рублей"
    };

    public static String numberToWords(long number) {
        if (number == 0) {
            return "ноль рублей";
        }

        if (number < 0) {
            return "минус " + numberToWords(Math.abs(number));
        }

        return convert(number, false);
    }


    private static String convert(long number, boolean female) {
            if (number < 10 && female) {
                return UNITSFEM[(int) number];
            } else if (number < 10) {
                return UNITS[(int) number];
            } else if (number == 10) {
                return TENS[1];
            } else if (number < 20) {
                return TEENS[(int) (number - 10)];
            } else if (number < 100 && female){
                return TENS[(int) (number / 10)] + (number % 10 > 0 ? " " + UNITSFEM[(int) (number % 10)] : "");
            } else if (number < 100) {
                return TENS[(int) (number / 10)] + (number % 10 > 0 ? " " + UNITS[(int) (number % 10)] : "");
            } else if (number < 1000) {
                return HUNDREDS[(int) (number / 100)] + (number % 100 > 0 ? " " + convert(number % 100, female) : "");
            } else if (number < 1000000) {
                return convert(number / 1000, true) + " " + getThousands(number / 1000) + (number % 1000 > 0 ? " " + convert(number % 1000, female) : "");
            } else if (number < 1000000000) {
                return convert(number / 1000000, false) + " " + getMillions(number / 1000000) + (number % 1000000 > 0 ? " " + convert(number % 1000000, false) : "");
            } else {
                return convert(number / 1000000000, false) + " " + getBillions(number / 1000000000) + (number % 1000000000 > 0 ? " " + convert(number % 1000000000, false) : "");
            }
    }


    private static String getThousands (long number){
        if (number >= 100){
            number = number % 100;
        }
        if (number >= 10 && number <= 20) {
            return THOUSANDS[3];
        }
        switch ((int) (number % 10)) {
                case 1:
                    return THOUSANDS[1];
                case 2:
                case 3:
                case 4:
                    return THOUSANDS[2];
                default:
                    return THOUSANDS[3];
        }
    }

    private static String getMillions (long number){
        if (number >= 100){
            number = number % 100;
        }

        if (number >= 10 && number <= 20) {
                return MILLIONS[3];
        }
        switch ((int) (number % 10)) {
                case 1:
                    return MILLIONS[1];
                case 2:
                case 3:
                case 4:
                    return MILLIONS[2];
                default:
                    return MILLIONS[3];
        }
    }

    private static String getBillions (long number){
        if (number >= 100){
            number = number % 100;
        }

            if (number >= 10 && number <= 20) {
                return BILLIONS[3];
            }
            switch ((int) (number % 10)) {
                case 1:
                    return BILLIONS[1];
                case 2:
                case 3:
                case 4:
                    return BILLIONS[2];
                default:
                    return BILLIONS[3];
            }
        }

        private static String getKopecks (long number){
            if (number >= 10 && number <= 20) {
                return KOPECKS[3];
            }
            switch ((int) (number % 10)) {
                case 1:
                    return KOPECKS[1];
                case 2:
                case 3:
                case 4:
                    return KOPECKS[2];
                default:
                    return KOPECKS[3];
            }
        }

        private static String getRubles(long number){
            if (number >= 10 && number <= 20) {
                return RUBLES[3];
            }
            switch ((int) (number % 10)) {
                case 1:
                    return RUBLES[1];
                case 2:
                case 3:
                case 4:
                    return RUBLES[2];
                default:
                    return RUBLES[3];
            }
        }

        public static String moneyInWords (String amount){
            long rubles = Long.parseLong(amount.substring(0, amount.length() - 2));
            long kopecks = Long.parseLong(amount.substring(amount.length() - 2));

            String kopecksString = amount.substring(amount.length() - 2);

            String rublesInWords = numberToWords(rubles) + " " + getRubles(rubles % 100);
            String kopecksInWords = kopecks > 0 ? kopecksString + " " + getKopecks(kopecks) : "";

            return capitalizeFirstLetter(rublesInWords + " " + kopecksInWords);
        }

    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

        public static void main (String[]args){
            System.out.println(moneyInWords("11211211200000"));


        }
    }


