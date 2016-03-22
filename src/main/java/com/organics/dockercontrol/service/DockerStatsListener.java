package com.organics.dockercontrol.service;


import com.alibaba.fastjson.JSONObject;
import com.organics.dockercontrol.cache.ContainersCache;
import com.organics.dockercontrol.dao.StatsDao;
import com.organics.dockercontrol.dto.StatsDto;
import com.organics.dockercontrol.entity.CPU;
import com.organics.dockercontrol.entity.Memory;
import com.organics.dockercontrol.entity.Stats;
import com.organics.dockercontrol.utils.Consts;
import com.organics.dockercontrol.utils.DateUtils;
import com.organics.dockercontrol.utils.MathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TransferQueue;

/**
 * Created by organics on 2016/3/12.
 */
public class DockerStatsListener implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(DockerStatsListener.class);

    private final TransferQueue<Map<String, String>> queue;
    private final StatsDao statsDao;

    public DockerStatsListener() {
        this.queue = ContainersCache.getQueue();
        this.statsDao = new StatsDao() {
        };
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (int i = 0; i < ContainersCache.getIdPathAndImage().size(); i++) {
                    if (i == 0) {
                        statsDao.save("======================================================================================================");
                        statsDao.save("host" + "\t\t\t\t" + "date" + "\t\t\t\t" + "cpu_usage" + "\t\t" + "memory_usage" + "\t\t" + "imageName");
                    }
                    queue.take().forEach((idPath, data) -> this.process(idPath, data));
                }
            }
        } catch (InterruptedException e) {
            logger.error("error :", e);
        }
    }

    private void process(final String idPath, final String statsData) {
        Stats stats = JSONObject.parseObject(statsData, Stats.class);
        if (stats == null) {
            return;
        }
        StatsDto statsDto = new StatsDto();
        // ----------对应镜像--------------------
        statsDto.setImageName(ContainersCache.getIdPathAndImage().get(idPath));
        // ----------计算时间--------------------
        statsDto.setDate(DateUtils.formatDockerDate(stats.getRead()));
        // ----------计算cpu--------------------
        CPU cpu = stats.getCpu_stats();
        long uscpu = cpu.getCpu_usage().getTotal_usage();
        long syscpu = cpu.getSystem_cpu_usage();
        statsDto.setCpu_usage(MathUtils.divide(uscpu, syscpu).toString());
        // ----------计算RAM-------------------
        Memory memory = stats.getMemory_stats();
        long usram = memory.getUsage();
        statsDto.setMemory_usage(MathUtils.conversions(usram).toString());

        statsDao.save(Consts.allContainerPathToHost(idPath), DateUtils.formatStatsDate(statsDto.getDate()), statsDto.getCpu_usage(), statsDto.getMemory_usage(), statsDto.getImageName());
    }
}