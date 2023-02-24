/*
 * This file is part of ViaBedrock - https://github.com/RaphiMC/ViaBedrock
 * Copyright (C) 2023 RK_01/RaphiMC and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.raphimc.viabedrock.protocol.storage;

import com.viaversion.viaversion.api.connection.StoredObject;
import com.viaversion.viaversion.api.connection.UserConnection;
import net.raphimc.viabedrock.protocol.model.ResourcePack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResourcePacksStorage extends StoredObject {

    private final Map<UUID, ResourcePack> resourcePacks = new HashMap<>();
    private final Map<UUID, ResourcePack> behaviorPacks = new HashMap<>();
    private boolean completed;

    public ResourcePacksStorage(final UserConnection user) {
        super(user);
    }

    public boolean hasResourcePack(final UUID packId) {
        return this.resourcePacks.containsKey(packId);
    }

    public ResourcePack getResourcePack(final UUID packId) {
        return this.resourcePacks.get(packId);
    }

    public void addResourcePack(final ResourcePack pack) {
        this.resourcePacks.put(pack.packId(), pack);
    }

    public boolean hasBehaviorPack(final UUID packId) {
        return this.behaviorPacks.containsKey(packId);
    }

    public ResourcePack getBehaviorPack(final UUID packId) {
        return this.behaviorPacks.get(packId);
    }

    public void addBehaviorPack(final ResourcePack pack) {
        this.behaviorPacks.put(pack.packId(), pack);
    }

    public boolean areAllPacksDecompressed() {
        return this.resourcePacks.values().stream().allMatch(ResourcePack::isDecompressed) && this.behaviorPacks.values().stream().allMatch(ResourcePack::isDecompressed);
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(final boolean completed) {
        this.completed = completed;
    }

}
