package com.carre.exercise.exercise;

import com.carre.exercise.exercise.dao.NaceDao;
import com.carre.exercise.exercise.dto.NaceDTO;
import com.carre.exercise.exercise.dto.NaceMapper;
import com.carre.exercise.exercise.service.NaceService;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import io.sniffy.Sniffy;
import io.sniffy.Spy;
import io.sniffy.sql.SqlQueries;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * @author Daniel Carretero Ferres
 */


public class NaceServiceTest extends AbstractJPATest
{
    @Autowired private NaceDao naceDao;
    @Autowired private DataSource dataSource;
    private final NaceMapper naceMapper = Mappers.getMapper(NaceMapper.class);
    private NaceService naceService;


    // DATABASE INICIALIZATION
    //--------------------------------------------------------------------------------------------------------
    private static final Operation operation = Operations.sequenceOf
    (
        Operations.deleteAllFrom("NACE"),

        Operations.insertInto("NACE")
            .columns("order_id", "order_level", "code", "parent", "description", "include_one",
                "include_two", "rulings", "exclude", "reference_isicv4")
            .values(111L, 1, "1", "10", "111", "111", "111", "111", "111", "111")
            .values(222L, 2, "2", "20", "222", "222", "222", "222", "222", "222")
            .build()
    );

    // BEFORE
    //--------------------------------------------------------------------------------------------------------
    @BeforeAll
    public void beforeAll()
    {
        naceService = new NaceService(naceDao, naceMapper);
    }

    @BeforeEach
    public void boforeEach()
    {
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetupTracker.launchIfNecessary(dbSetup);
        profiler.reset();
    }

    // TEST
    //--------------------------------------------------------------------------------------------------------
    @Nested @DisplayName("GIVEN: a Nace table")
    public class whenNaceTableIsEmpty
    {
        @Nested @DisplayName("WHEN: create a Nace that doesn't exist")
        public class whenCreateNoExistingNace
        {
            @Test
            @DisplayName("THEN: save Nace into H2BD")
            public void thenSaveNaceInDB()
            {
                NaceDTO naceDTO = NaceDTO.builder()
                    .orderId(333L).orderLevel(3).code("30").parent("333").description("333")
                    .includeOne("333").includeTwo("333").rulings("333").exclude("333")
                    .referenceIsicV4("333").build();

                naceService.createNace(naceDTO);

                NaceDTO result = naceService.getNaceDetails(naceDTO.getOrderId());

                assertThat(result).isNotNull();
                assertThat(result).usingRecursiveComparison().isEqualTo(naceDTO);
            }

            @Test
            @DisplayName("THEN: entity saved with only one database call")
            public void thenSaveWithOneCall()
            {
                NaceDTO naceDTO = NaceDTO.builder()
                    .orderId(333L).orderLevel(3).code("30").parent("333").description("333")
                    .includeOne("333").includeTwo("333").rulings("333").exclude("333")
                    .referenceIsicV4("333").build();

                naceService.createNace(naceDTO);

                profiler.verify(SqlQueries.atMostOneQuery());
            }

        }

        @Nested @DisplayName("WHEN: create a Nace that already exist")
        public class whenCreateExistingNace
        {
            @Test @DisplayName("THEN: Throw an exception")
            public void thenNothingHappens()
            {
                NaceDTO naceDTO1 = NaceDTO.builder()
                    .orderId(111L).orderLevel(3).code("30").parent("333").description("333")
                    .includeOne("333").includeTwo("333").rulings("333").exclude("333")
                    .referenceIsicV4("333").build();
                try
                {
                    naceService.createNace(naceDTO1);
                    fail("Exception not thrown");
                }
                catch (Exception e)
                {

                }
            }

        }

    }

    @Nested @DisplayName("GIVEN: a Nace table")
    public class whenNaceTable
    {
        @Nested @DisplayName("WHEN: get details by no existing order")
        public class whenGetDetailsByNoExistingId
        {
            @Test @DisplayName("THEN: Throw Not Found Exception")
            public void thenThrowNotFoundException()
            {
                try
                {
                    naceService.getNaceDetails(1L);
                    Assertions.fail("e");
                }
                catch (Exception e)
                {

                }

            }

        }

        @Nested @DisplayName("WHEN: get details by existing order")
        public class whenGetDetailsByExistingId
        {
            @Test @DisplayName("THEN: return Nace DTO")
            public void thenReturnNaceDTO()
            {

            }

        }

    }


}
