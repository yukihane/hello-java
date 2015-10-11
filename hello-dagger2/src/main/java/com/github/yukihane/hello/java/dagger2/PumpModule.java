package com.github.yukihane.hello.java.dagger2;

import dagger.Module;
import dagger.Provides;

@Module
class PumpModule {
    @Provides
    Pump providePump(Thermosiphon pump) {
        return pump;
    }
}
