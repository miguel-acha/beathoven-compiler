package org.beathoven;

import org.beathoven.generator.BeathovenGenerator;
import org.beathoven.scoping.BeathovenScopeProvider;
import org.eclipse.xtext.generator.IGenerator2;
import org.eclipse.xtext.scoping.IScopeProvider;

public class BeathovenRuntimeModule extends AbstractBeathovenRuntimeModule {

    @Override
    public Class<? extends IScopeProvider> bindIScopeProvider() {
        return BeathovenScopeProvider.class;
    }

    @Override
    public Class<? extends IGenerator2> bindIGenerator2() {
        return BeathovenGenerator.class;
    }
}
