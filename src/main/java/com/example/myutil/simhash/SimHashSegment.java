package com.example.myutil.simhash;

import java.io.Serializable;
import java.util.List;

/**
 * sim hash 分片
 */
public class SimHashSegment implements Serializable {


    private static final long serialVersionUID = 2414305438781207596L;

    // 摘要
    private String digest;

    // 短Hash
    private String subHash;

    // 全量Hash
    private String fullSim;

    private List<SimHashWord> dimensions;

    public SimHashSegment(String subHash, String fullSim, List<SimHashWord> dimensions) {
        this.subHash = subHash;
        this.fullSim = fullSim;
        this.dimensions = dimensions;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getSubHash() {
        return subHash;
    }

    public void setSubHash(String subHash) {
        this.subHash = subHash;
    }

    public String getFullSim() {
        return fullSim;
    }

    public void setFullSim(String fullSim) {
        this.fullSim = fullSim;
    }

    public List<SimHashWord> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<SimHashWord> dimensions) {
        this.dimensions = dimensions;
    }
}
