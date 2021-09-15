package com.carre.exercise.exercise;

import com.ninja_squad.dbsetup.DbSetupTracker;
import io.sniffy.Sniffy;
import io.sniffy.Spy;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Daniel Carretero Ferres
 */

@ExtendWith(SpringExtension.class) @DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional(propagation= Propagation.NOT_SUPPORTED)
public abstract class AbstractJPATest
{
    protected final Spy<?> profiler = Sniffy.spy();
    protected final DbSetupTracker dbSetupTracker = new DbSetupTracker();
}
