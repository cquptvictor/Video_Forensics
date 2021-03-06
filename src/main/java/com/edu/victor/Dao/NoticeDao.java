package com.edu.victor.Dao;

import com.edu.victor.domain.Notice;
import com.edu.victor.domain.NoticeDto;
import com.edu.victor.domain.Page;

public interface NoticeDao {
    Boolean addNotice(Notice notice);
    Boolean deleteNotice(int id);
    Page<NoticeDto> searchNoticeByPage(Page page);
    Notice getNotice(int id);

}
