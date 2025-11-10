package dev.juici.jooz.component;

import dev.juici.jooz.factor.FactorGroup;
import org.ladysnake.cca.api.v3.component.ComponentV3;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import java.util.Set;

public interface FactorComponent extends ComponentV3, AutoSyncedComponent {
    Set<FactorGroup> getFactorGroups();
    void addGroup(FactorGroup group);
    void removeGroup(FactorGroup group);

    FactorGroup getActiveGroup();
    void setActiveGroup(FactorGroup group);
}