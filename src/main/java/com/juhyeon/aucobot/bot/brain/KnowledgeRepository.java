package com.juhyeon.aucobot.bot.brain;

import com.juhyeon.aucobot.bot.brain.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeRepository extends JpaRepository<Knowledge, Integer> {
}
