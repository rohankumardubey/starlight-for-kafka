/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.streamnative.pulsar.handlers.kop.schemaregistry.model.impl;

import io.streamnative.pulsar.handlers.kop.schemaregistry.model.SchemaStorageAccessor;
import java.util.concurrent.ConcurrentHashMap;

public class MemorySchemaStorageAccessor implements SchemaStorageAccessor {
    private final ConcurrentHashMap<String, MemorySchemaStorage> tenants = new ConcurrentHashMap<>();

    @Override
    public MemorySchemaStorage getSchemaStorageForTenant(String tenant) {
        return tenants.computeIfAbsent(tenant != null ? tenant : "", t -> new MemorySchemaStorage(t));
    }

    public void clear() {
        tenants.clear();
    }

    public void close() {
        tenants.clear();
    }
}
