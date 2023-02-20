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
package net.raphimc.viabedrock.protocol.types.chunk;

import com.viaversion.viaversion.api.type.Type;
import io.netty.buffer.ByteBuf;
import net.raphimc.viabedrock.api.chunk.section.BedrockChunkSection;

public class ChunkSectionType extends Type<BedrockChunkSection> {

    private final Type<BedrockChunkSection> V0 = new ChunkSectionV0Type();
    private final Type<BedrockChunkSection> V1 = new ChunkSectionV1Type();
    private final Type<BedrockChunkSection> V8 = new ChunkSectionV8Type();
    private final Type<BedrockChunkSection> V9 = new ChunkSectionV9Type();

    public ChunkSectionType() {
        super(BedrockChunkSection.class);
    }

    @Override
    public BedrockChunkSection read(ByteBuf buffer) throws Exception {
        final byte version = buffer.readByte(); // version

        switch (version) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return V0.read(buffer);
            case 1:
                return V1.read(buffer);
            case 8:
                return V8.read(buffer);
            case 9:
                return V9.read(buffer);
            default:
                throw new UnsupportedOperationException("Unknown chunk section version: " + version);
        }
    }

    @Override
    public void write(ByteBuf buffer, BedrockChunkSection value) throws Exception {
        throw new UnsupportedOperationException("ChunkSectionType is only for reading");
    }

}
