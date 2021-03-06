package com.organics.dockercontrol.dao;

import com.organics.dockercontrol.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by wanghc on 2016/3/15.
 */
public interface StatsDao {

    static final Logger logger = LoggerFactory.getLogger(StatsDao.class);

    default void save(String... args) {
        File file = new File("stats");
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(file, DateUtils.formatFileNameDate(new Date()) + ".txt");
        try (BufferedWriter bufw = new BufferedWriter(new FileWriter(file, true))) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < args.length; i++) {
                sb.append(args[i]);
                if (i != args.length - 1) {
                    sb.append("\t\t");
                }
            }
            System.out.println(sb.toString());
            bufw.write(sb.toString());
            bufw.newLine();
            bufw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
