package com.yasar;

import com.yasar.entity.Sehir;
import com.yasar.utility.EBolge;

import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;


public class Runner {
    public static void main(String[] args) {

        SehirManager manager = new SehirManager();
        List<Sehir> sehirList = manager.sehirleriOlustur();

        //Soru 1 - (a) Sehir s�n�f�ndan olu�an bir List'ten, belirli bir b�lgedeki �ehirlerin Listesini elde
        //etmek i�in bir Java Stream ifadesi yaz�n.
        System.out.println("------------   BOLGE 3 SEHIRLER LISTESI   ------------");
        sehirList.stream()
                .filter(x -> x.getBolge().equals(EBolge.BOLGE_3))
                .toList().forEach(System.out::println);


        //Soru 1 - (b) Sehir s�n�f�ndan olu�an bir List'ten, belirli bir b�lgedeki �ehirlerin isimlerini i�eren bir
        //Liste elde etmek i�in bir Java Stream ifadesi yaz�n.
        System.out.println("------------   BOLGE 1 SEHIR ISIMLERI LISTESI  ------------");
        sehirList.stream()
                .filter(x -> x.getBolge().equals(EBolge.BOLGE_1))
                .map(y -> y.getIsim())
                .toList()
                .forEach(System.out::println);


        //Soru 2 - Sehir s�n�f�ndan olu�an bir List'ten, belirli bir n�fus aral���na sahip �ehirlerin plaka
        //numaralar�n� i�eren bir Set elde etmek i�in bir Java Stream ifadesi yaz�n.
        System.out.println("------------   BELLI NUFUS ARASINDAKI SEHIRLER�N PLAKASI  ------------");
        sehirList.stream()
                .filter(x -> x.getNufus() > 300_000 && x.getNufus() < 2_130_000)
                .map(y -> y.getPlakaNo())
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        //Soru 3 - (a) Sehir s�n�f�ndan olu�an bir List'ten, belirli bir harfle ba�layan �ehir adlar�n� i�eren bir
        //List elde etmek i�in bir Java Stream ifadesi yaz�n.
        System.out.println("------------   B ILE BASLAYAN SEHIRLER�N ADLARI  ------------");
        sehirList.stream()
                .filter(x -> x.getIsim().startsWith("B"))
                .map(y -> y.getIsim())
                .toList()
                .forEach(System.out::println);


        //Soru 3 - (b) Sehir s�n�f�ndan olu�an bir List'ten, belirli bir harfle ba�layan �ehir adlar�n�n harf
        //uzunluklar� toplam�n� bulan Java Stream ifadesi yaz�n.
        System.out.println("------------   A ILE BASLAYAN SEHIRLER�N HARF UZUNLUK TOPLAMI  ------------");
        int harfToplam = sehirList.stream()
                .filter(x -> x.getIsim().startsWith("A"))
                .mapToInt(y -> y.getIsim().length()).sum();
        System.out.println(harfToplam);

        //Soru 4 - Sehir s�n�f�ndan olu�an bir List'ten, butun sehirlerin nufus toplam�n� bulmak i�in bir
        //Java Stream ifadesi yaz�n.
        System.out.println("------------   N�FUSLARIN TOPLAMI  ------------");
        long toplamNufus = sehirList.stream()
                .mapToLong(Sehir::getNufus).sum();
        System.out.println(toplamNufus);

        //Soru 5 - Sehir s�n�f�ndan olu�an bir List'ten, belirli bir b�lgedeki nufus toplam� ,ortalamas�, en
        //b�y�k n�fusu gibi istatistikleri bulmak i�in bir Java Stream ifadesi yaz�n.
        System.out.println("------------   ISTATISTIKLER  ------------");
        LongSummaryStatistics statistics = sehirList.stream()
                .filter(x -> x.getBolge().equals(EBolge.BOLGE_1))
                .mapToLong(x -> x.getNufus())
                .summaryStatistics();
        System.out.println("B�lge N�fus Toplam� ........ : " + statistics.getSum());
        System.out.println("B�lge N�fus Ortalamas� ..... : " + statistics.getAverage());
        System.out.println("B�lge N�fus Toplam� ........ : " + statistics.getMax());

        //Soru 6 - Sehir s�n�f�ndan olu�an bir List'ten, belirli bir n�fus aral���ndaki �ehirlerin ortalama
        //n�fusunu hesaplamak i�in bir Java Stream ifadesi yaz�n.
        System.out.println("------------   BEL�RL� N�FUS ARALI�INDAK� �EH�RLER�N ORTALAMA N�FUSU  ------------");
        sehirList.stream()
                .filter(x -> x.getNufus() > 500_000 && x.getNufus() < 3_300_000)
                .mapToLong(x -> x.getNufus()).average()
                .ifPresent(r -> System.out.println());

        //Soru 7 - �ehirler listesini, n�fusu 1 milyondan fazla olan �ehirleri i�erecek �ekilde filtreleyin.
        //Filtrelenmi� listeyi ekrana yazd�r�n.
        System.out.println("------------   N�FUSU 1 M�LYONDAN FAZLA OLAN SEH�RLER  ------------");
        sehirList.stream()
                .filter(x -> x.getNufus() > 1_000_000)
                .toList()
                .forEach(System.out::println);

        //Soru 8 - �ehirler listesini, �ehir adlar�n� b�y�k harfe d�n��t�recek �ekilde map i�lemine tabi
        //tutun. D�n��t�r�lm�� listeyi ekrana yazd�r�n.
        System.out.println("------------   SEH�R ADLARI B�Y�K HARF  ------------");
        sehirList.stream()
                .map(x -> x.getIsim().toUpperCase())
                .toList()
                .forEach(System.out::println);

        //Soru 9 - Sehir s�n�f�ndan olu�an bir List'ten, belirli bir b�lgedeki �ehirlerin plaka numaralar�n�n
        //tek olanlar� i�eren bir List elde etmek i�in bir Java Stream ifadesi yaz�n.
        System.out.println("------------   B�LGEDEK� TEK PLAKALI SEH�RLER  ------------");
        sehirList.stream()
                .filter(x -> x.getBolge().equals(EBolge.BOLGE_2) && Integer.parseInt(x.getPlakaNo()) % 2 == 1)
                .toList()
                .forEach(System.out::println);

        //Soru 10 - Sehir s�n�f�ndan olu�an bir List'ten, belirli bir b�lgedeki �ehirlerin adlar�n�n ilk 3 harfini
        //ve plaka numaralar�n� aralar�nda - birle�tirerek yeni bir liste elde etmek i�in bir Java
        //Stream ifadesi yaz�n.(01-Ada ,02-Ad�,03-Afy,04-A�r,05-Ama,06-Ank��.) gibi
        System.out.println("------------   B�LGEDEK� SEH�R ADLARININ ILK 3 HARFI VE PLAKALARI  ------------");
        sehirList.stream()
                .filter(x->x.getBolge().equals(EBolge.BOLGE_4))
                .map(x->x.getPlakaNo()+"-"+x.getIsim().substring(0,4))
                .toList()
                .forEach(System.out::println);
    }
}
