package com.deals.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class TestQuery implements GraphQLQueryResolver {

    public String testQuery() {
        return "Working .....";
    }
}
