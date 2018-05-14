package ge.economy.law.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AbstractDAO {

    @Autowired
    protected DSLContext dslContext;
}
