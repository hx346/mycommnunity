package com.example.mycommunity.mapper;

import com.example.mycommunity.model.Nav;
import com.example.mycommunity.model.NavExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface NavMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAV
     *
     * @mbg.generated Sat Oct 26 17:34:59 CST 2019
     */
    long countByExample(NavExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAV
     *
     * @mbg.generated Sat Oct 26 17:34:59 CST 2019
     */
    int deleteByExample(NavExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAV
     *
     * @mbg.generated Sat Oct 26 17:34:59 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAV
     *
     * @mbg.generated Sat Oct 26 17:34:59 CST 2019
     */
    int insert(Nav record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAV
     *
     * @mbg.generated Sat Oct 26 17:34:59 CST 2019
     */
    int insertSelective(Nav record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAV
     *
     * @mbg.generated Sat Oct 26 17:34:59 CST 2019
     */
    List<Nav> selectByExampleWithRowbounds(NavExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAV
     *
     * @mbg.generated Sat Oct 26 17:34:59 CST 2019
     */
    List<Nav> selectByExample(NavExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAV
     *
     * @mbg.generated Sat Oct 26 17:34:59 CST 2019
     */
    Nav selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAV
     *
     * @mbg.generated Sat Oct 26 17:34:59 CST 2019
     */
    int updateByExampleSelective(@Param("record") Nav record, @Param("example") NavExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAV
     *
     * @mbg.generated Sat Oct 26 17:34:59 CST 2019
     */
    int updateByExample(@Param("record") Nav record, @Param("example") NavExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAV
     *
     * @mbg.generated Sat Oct 26 17:34:59 CST 2019
     */
    int updateByPrimaryKeySelective(Nav record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAV
     *
     * @mbg.generated Sat Oct 26 17:34:59 CST 2019
     */
    int updateByPrimaryKey(Nav record);
}