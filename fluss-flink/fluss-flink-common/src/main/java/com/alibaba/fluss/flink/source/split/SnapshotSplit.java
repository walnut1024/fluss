/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.fluss.flink.source.split;

import com.alibaba.fluss.metadata.TableBucket;

import javax.annotation.Nullable;

import java.util.Objects;

/** The split for snapshot. It's used to describe a snapshot of a table bucket. */
public abstract class SnapshotSplit extends SourceSplitBase {

    /** The records to skip when reading the snapshot. */
    protected final long recordsToSkip;

    /** The snapshot id. It's used to identify the snapshot for a kv bucket. */
    protected final long snapshotId;

    public SnapshotSplit(
            TableBucket tableBucket,
            @Nullable String partitionName,
            long snapshotId,
            long recordsToSkip) {
        super(tableBucket, partitionName);
        this.snapshotId = snapshotId;
        this.recordsToSkip = recordsToSkip;
    }

    public SnapshotSplit(TableBucket tableBucket, @Nullable String partitionName, long snapshotId) {
        this(tableBucket, partitionName, snapshotId, 0);
    }

    public long getSnapshotId() {
        return snapshotId;
    }

    public long recordsToSkip() {
        return recordsToSkip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SnapshotSplit that = (SnapshotSplit) o;
        return recordsToSkip == that.recordsToSkip && Objects.equals(snapshotId, that.snapshotId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), recordsToSkip, snapshotId);
    }

    @Override
    public String toString() {
        return "SnapshotSplit{"
                + "tableBucket="
                + tableBucket
                + ", partitionName='"
                + partitionName
                + '\''
                + ", recordsToSkip="
                + recordsToSkip
                + ", snapshotId="
                + snapshotId
                + '}';
    }
}
