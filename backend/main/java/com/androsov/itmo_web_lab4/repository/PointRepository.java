package com.androsov.itmo_web_lab4.repository;

import com.androsov.itmo_web_lab4.model.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
public interface PointRepository extends CrudRepository<Point, Long> {
    Collection<Point> findAllByUserId(long user_id);

    @Transactional
    void deleteAllByUserId(long user_id);
}