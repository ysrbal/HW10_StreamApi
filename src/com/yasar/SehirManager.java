package com.yasar;

import com.yasar.entity.Sehir;
import com.yasar.utility.EBolge;

import static com.yasar.utility.SehirDatabase.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SehirManager {
    Random random = new Random();
    private Sehir sehir;

    public List<Sehir> sehirleriOlustur() {
        List<Sehir> sehirList = new ArrayList<>();

            int randomValue = random.nextInt(sehirler.length);
            String isim = sehirler[randomValue];
            long nufus = random.nextLong(100_000, 5_000_001);
            String plaka = "";
            for (int i = 0; i < sehirler.length; i++) {
                if (sehirler[i].equals(isim)) {
                    if (i < 9) {
                        plaka = "0" + (i + 1);
                    } else plaka = String.valueOf(i + 1);
                    break;
                }
            }
            EBolge[] bolgeler = EBolge.values();
            EBolge bolge = bolgeler[random.nextInt(bolgeler.length)];

            sehirList.add(new Sehir(isim, nufus, plaka, bolge));

        return sehirList;
    }

}
