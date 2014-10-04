package system;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * Created by Leite NA on 14-10-2.
 * This class is used for the tests with Protected
 */
public class TestMyPhonyForProtected extends PhonyList {

    /**
     * Some tests for removeRange(int.int).
     * @passed Yes
     * @type Functional
     * @see system.PhonyList#removeRange(int, int)
     */
    @Test
    public void testRemoveRange(){
        PhonyList<Integer> l=new PhonyList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.removeRange(1, 3);
        assertEquals(1,l.size());
        assertEquals((Integer)1,l.get(0));
    }

    /**
     * Some tests for removeRange(int.int). It will rise an exception.
     * @passed Yes
     * @type Functional   
     * @see system.PhonyList#removeRange(int, int)
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveRangeWithToIndexLarge(){
        PhonyList<Integer> l=new PhonyList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        int index=5;
        l.removeRange(1, index);

    }

    /**
     * Some tests for removeRange(int.int) It will rise an exception.
     * @passed Yes
     * @type Functional
     * @see system.PhonyList#removeRange(int, int)
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveRangeWithFromIndexLarge(){
        PhonyList<Integer> l=new PhonyList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        int index=5;
        l.removeRange(index, 2);

    }

    /**
     * Some tests for removeRange(int.int).It will rise an exception.
     * @passed Yes
     * @type Functional
     * @see system.PhonyList#removeRange(int, int)
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveRangeWithFromIndexSize(){
        PhonyList<Integer> l=new PhonyList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        int index=l.size();
        l.removeRange(index,1);

    }

    /**
     * Some tests for removeRange(int.int). It will rise an exception.
     * @passed Yes
     * @type Functional
     * @see system.PhonyList#removeRange(int, int)
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveRangeWithIndexNeg(){
        PhonyList<Integer> l=new PhonyList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        int index=-1;
        l.removeRange(index, 2);

    }

    /**
     * Some tests for removeRange(int.int), It will rise an exception.
     * @passed Yes
     * @type Functional
     * @see system.PhonyList#removeRange(int, int)
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveRangeWithIndexFault(){
        PhonyList<Integer> l=new PhonyList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        int index=5;
        l.removeRange(index, 2);


    }

    /**
     * Some tests for removeRange(int.int). This test is for prove that if we use the same indexes for the parameters.
     * It will not change anything in the list.
     * @passed Yes
     * @type Functional
     * @see system.PhonyList#removeRange(int, int)
     */
    @Test
    public void testRemoveRangeWithSameIndex(){
        PhonyList<Integer> l=new PhonyList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        PhonyList<Integer> exp=l;
        int index=2;
        l.removeRange(index, index);

        assertEquals(exp,l);
    }
}
