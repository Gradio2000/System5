package com.example.docs.dao;

import com.example.docs.dto.DocsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class DataBaseUserQueryes {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DocsDto> getDocsByFullTextSearch(String textQuery){
        String param = "SELECT * FROM d_docs where plainto_tsquery('russian', "  + "'" + textQuery + "')  @@ d_docs.tsv";
        List<DocsDto> docsDtoList = jdbcTemplate.query(
                param,
                new RowMapper<>() {
            @Override
            public DocsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                DocsDto docsDto = new DocsDto();
                docsDto.setDocId(rs.getInt("doc_id"));
                docsDto.setDocName(rs.getString("doc_name"));
                docsDto.setFileName(rs.getString("file_name"));
                docsDto.setBusinessId(rs.getInt("business_id"));
                docsDto.setRegNumber(rs.getString("reg_number"));
                return docsDto;
            }
        });

        return docsDtoList;
    }
}
