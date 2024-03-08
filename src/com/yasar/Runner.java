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

        //Soru 1 - (a) Sehir sýnýfýndan oluþan bir List'ten, belirli bir bölgedeki þehirlerin Listesini elde
        //etmek için bir Java Stream ifadesi yazýn.
        System.out.println("------------   BOLGE 3 SEHIRLER LISTESI   ------------");
        sehirList.stream()
                .filter(x -> x.getBolge().equals(EBolge.BOLGE_3))
                .toList().forEach(System.out::println);


        //Soru 1 - (b) Sehir sýnýfýndan oluþan bir List'ten, belirli bir bölgedeki þehirlerin isimlerini içeren bir
        //Liste elde etmek için bir Java Stream ifadesi yazýn.
        System.out.println("------------   BOLGE 1 SEHIR ISIMLERI LISTESI  ------------");
        sehirList.stream()
                .filter(x -> x.getBolge().equals(EBolge.BOLGE_1))
                .map(y -> y.getIsim())
                .toList()
                .forEach(System.out::println);


        //Soru 2 - Sehir sýnýfýndan oluþan bir List'ten, belirli bir nüfus aralýðýna sahip þehirlerin plaka
        //numaralarýný içeren bir Set elde etmek için bir Java Stream ifadesi yazýn.
        System.out.println("------------   BELLI NUFUS ARASINDAKI SEHIRLERÝN PLAKASI  ------------");
        sehirList.stream()
                .filter(x -> x.getNufus() > 300_000 && x.getNufus() < 2_130_000)
                .map(y -> y.getPlakaNo())
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        //Soru 3 - (a) Sehir sýnýfýndan oluþan bir List'ten, belirli bir harfle baþlayan þehir adlarýný içeren bir
        //List elde etmek için bir Java Stream ifadesi yazýn.
        System.out.println("------------   B ILE BASLAYAN SEHIRLERÝN ADLARI  ------------");
        sehirList.stream()
                .filter(x -> x.getIsim().startsWith("B"))
                .map(y -> y.getIsim())
                .toList()
                .forEach(System.out::println);


        //Soru 3 - (b) Sehir sýnýfýndan oluþan bir List'ten, belirli bir harfle baþlayan þehir adlarýnýn harf
        //uzunluklarý toplamýný bulan Java Stream ifadesi yazýn.
        System.out.println("------------   A ILE BASLAYAN SEHIRLERÝN HARF UZUNLUK TOPLAMI  ------------");
        int harfToplam = sehirList.stream()
                .filter(x -> x.getIsim().startsWith("A"))
                .mapToInt(y -> y.getIsim().length()).sum();
        System.out.println(harfToplam);

        //Soru 4 - Sehir sýnýfýndan oluþan bir List'ten, butun sehirlerin nufus toplamýný bulmak için bir
        //Java Stream ifadesi yazýn.
        System.out.println("------------   NÜFUSLARIN TOPLAMI  ------------");
        long toplamNufus = sehirList.stream()
                .mapToLong(Sehir::getNufus).sum();
        System.out.println(toplamNufus);

        //Soru 5 - Sehir sýnýfýndan oluþan bir List'ten, belirli bir bölgedeki nufus toplamý ,ortalamasý, en
        //büyük nüfusu gibi istatistikleri bulmak için bir Java Stream ifadesi yazýn.
        System.out.println("------------   ISTATISTIKLER  ------------");
        LongSummaryStatistics statistics = sehirList.stream()
                .filter(x -> x.getBolge().equals(EBolge.BOLGE_1))
                .mapToLong(x -> x.getNufus())
                .summaryStatistics();
        System.out.println("Bölge Nüfus Toplamý ........ : " + statistics.getSum());
        System.out.println("Bölge Nüfus Ortalamasý ..... : " + statistics.getAverage());
        System.out.println("Bölge Nüfus Toplamý ........ : " + statistics.getMax());

        //Soru 6 - Sehir sýnýfýndan oluþan bir List'ten, belirli bir nüfus aralýðýndaki þehirlerin ortalama
        //nüfusunu hesaplamak için bir Java Stream ifadesi yazýn.
        System.out.println("------------   BELÝRLÝ NÜFUS ARALIÐINDAKÝ ÞEHÝRLERÝN ORTALAMA NÜFUSU  ------------");
        sehirList.stream()
                .filter(x -> x.getNufus() > 500_000 && x.getNufus() < 3_300_000)
                .mapToLong(x -> x.getNufus()).average()
                .ifPresent(r -> System.out.println());

        //Soru 7 - Þehirler listesini, nüfusu 1 milyondan fazla olan þehirleri içerecek þekilde filtreleyin.
        //Filtrelenmiþ listeyi ekrana yazdýrýn.
        System.out.println("------------   NÜFUSU 1 MÝLYONDAN FAZLA OLAN SEHÝRLER  ------------");
        sehirList.stream()
                .filter(x -> x.getNufus() > 1_000_000)
                .toList()
                .forEach(System.out::println);

        //Soru 8 - Þehirler listesini, þehir adlarýný büyük harfe dönüþtürecek þekilde map iþlemine tabi
        //tutun. Dönüþtürülmüþ listeyi ekrana yazdýrýn.
        System.out.println("------------   SEHÝR ADLARI BÜYÜK HARF  ------------");
        sehirList.stream()
                .map(x -> x.getIsim().toUpperCase())
                .toList()
                .forEach(System.out::println);

        //Soru 9 - Sehir sýnýfýndan oluþan bir List'ten, belirli bir bölgedeki þehirlerin plaka numaralarýnýn
        //tek olanlarý içeren bir List elde etmek için bir Java Stream ifadesi yazýn.
        System.out.println("------------   BÖLGEDEKÝ TEK PLAKALI SEHÝRLER  ------------");
        sehirList.stream()
                .filter(x -> x.getBolge().equals(EBolge.BOLGE_2) && Integer.parseInt(x.getPlakaNo()) % 2 == 1)
                .toList()
                .forEach(System.out::println);

        //Soru 10 - Sehir sýnýfýndan oluþan bir List'ten, belirli bir bölgedeki þehirlerin adlarýnýn ilk 3 harfini
        //ve plaka numaralarýný aralarýnda - birleþtirerek yeni bir liste elde etmek için bir Java
        //Stream ifadesi yazýn.(01-Ada ,02-Adý,03-Afy,04-Aðr,05-Ama,06-Ank…….) gibi
        System.out.println("------------   BÖLGEDEKÝ SEHÝR ADLARININ ILK 3 HARFI VE PLAKALARI  ------------");
        sehirList.stream()
                .filter(x->x.getBolge().equals(EBolge.BOLGE_4))
                .map(x->x.getPlakaNo()+"-"+x.getIsim().substring(0,4))
                .toList()
                .forEach(System.out::println);
    }
}
