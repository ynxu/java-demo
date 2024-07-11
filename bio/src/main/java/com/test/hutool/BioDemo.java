package com.test.hutool;

import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.core.alignment.template.AlignedSequence;
import org.biojava.nbio.core.alignment.template.Profile;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.biojava.nbio.core.sequence.io.FastaReaderHelper;
import org.biojava.nbio.core.util.ConcurrencyTools;
import org.biojava.nbio.phylo.TreeConstructor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BioDemo {

    public static void test(String[] args) {
        String[] ids = new String[]{
                "004_KJ513477_11029",
//                "004_KR012466_11029",
//                "004_KR012467_11029",
//                "004_KR012468_11029",
//                "004_KT321307_11029",
                "004_MG663513_11034",
                "004_NC_002803_11019",
        };
        if (args.length > 1) {
            ids = args;
        }
        try {
            multipleSequenceAlignment(ids);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            ConcurrencyTools.shutdown();
        }
    }

    private static void multipleSequenceAlignment(String[] ids) throws Exception {
        List<ProteinSequence> lst = new ArrayList<ProteinSequence>();
        for (String id : ids) {
            lst.add(getSequenceForId(id));
        }
        Profile<ProteinSequence, AminoAcidCompound> profile = Alignments.getMultipleSequenceAlignment(lst);
        System.out.printf("Clustalw:%n%s%n", profile);
        System.out.println(profile.getSize());
        for (AlignedSequence<ProteinSequence, AminoAcidCompound> alignedSequence : profile.getAlignedSequences()) {
            System.out.println(alignedSequence.getAccession());
            System.out.println(alignedSequence.getSequenceAsString());
        }

    }

    private static ProteinSequence getSequenceForId(String uniProtId) throws Exception {
        InputStream inStream = TreeConstructor.class.getResourceAsStream(String.format("/%s.fasta", uniProtId));
        ProteinSequence seq = FastaReaderHelper.readFastaProteinSequence(inStream).get(uniProtId);
        System.out.printf("id : %s %s", uniProtId, seq, System.getProperty("line.separator"), seq.getOriginalHeader());
        System.out.println();
        return seq;
    }

}
