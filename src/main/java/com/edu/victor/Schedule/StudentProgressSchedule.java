package com.edu.victor.Schedule;

import com.edu.victor.Dao.CourseDao;
import com.edu.victor.domain.VideoPlaySituation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**定时更新学生的学习进度*/
@Component
public class StudentProgressSchedule {
    @Autowired
    CourseDao courseDao;
    /**1.得到所有课程
     * 2.得到课程下的学生
     * 3.得到学生的视频播放进度
     * 4.计算学生学习进度
     * 5.更新到数据库
     */
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void updateStudentStudyProgress(){
       List<Integer> courses = courseDao.getAllCourse();
       List<VideoPlaySituation> videoPlaySituations = new ArrayList<>();
       System.out.println("更新学生学习进度");
        for(Integer courseId : courses){
            List<Integer> students = courseDao.getAllStudentsByCourse(courseId);
            for(Integer studentId : students){
                VideoPlaySituation videoPlaySituation = new VideoPlaySituation();
                videoPlaySituation.setStuId(studentId);
                videoPlaySituation.setCourseId(courseId);

                VideoPlaySituation temp = courseDao.getStudentVideoPlaySituation(videoPlaySituation);
                videoPlaySituation.setProgress((temp.getAlreadyOver()/temp.getTotal()*100.0));
                videoPlaySituations.add(videoPlaySituation);
            }
        }
        for(VideoPlaySituation videoPlaySituation : videoPlaySituations)
        {
            courseDao.updateStudentStudyProgress(videoPlaySituation);
        }
    }
}
