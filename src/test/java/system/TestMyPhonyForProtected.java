package system;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * My own tests for protected methods.
 * Created by Leite NA on 28 Sept 2014.
 * This class is used for the tests with Protected
 * @author NA Leite
 * @since 28 Sept 2014
 * */
public class TestMyPhonyForProtected extends PhonyList {

    /**
     * Some tests for removeRange(int.int).
     * @passed Yes
     * @type Functional
     * @see system.PhonyList#removeRange(int, int)
     * @input list[1,2,3,4] fIdx=1, tIdx=3
     * @oracle The new list contains the element which are not removed but does not contains the element inside of the range.
     */
    @Test
    public void testRemoveRange(){
        PhonyList<Integer> l=new PhonyList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.removeRange(1, 3);
        assertEquals(2,l.size());
        assertEquals((Integer)1,l.get(0));
        assertEquals((Integer)4,l.get(1));
    }

    /**
     * Some tests for removeRange(int.int) with a large toIndex. It will rise an exception.
     * @passed Yes
     * @type Functional   
     * @see system.PhonyList#removeRange(int, int)
     * @input list[1,2,3] fIdx=1, tIdx=5
     * @oracle The exception IndexOutOfBoundsException rises.
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
     * Some tests for removeRange(int.int) with a large fromIndex It will rise an exception.
     * @passed Yes
     * @type Functional
     * @see system.PhonyList#removeRange(int, int)
     * @input list[1,2,3] fIdx=5, tIdx=2
     * @oracle The exception IndexOutOfBoundsException rises.
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
     * Some tests for removeRange(int.int) with a fromIndex=size.It will rise an exception.
     * @passed Yes
     * @type Functional
     * @see system.PhonyList#removeRange(int, int)
     * @input list[1,2,3,4] fIdx=size, tIdx=3
     * @oracle The exception IndexOutOfBoundsException rises.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveRangeWithFromIndexSize(){
        PhonyList<Integer> l=new PhonyList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        int index=l.size();
        l.removeRange(index,3);

    }

    /**
     * Some tests for removeRange(int.int) with a negative fromIndex. It will rise an exception.
     * @passed Yes
     * @type Functional
     * @see system.PhonyList#removeRange(int, int)
     * @input list[1,2,3] fIdx=-1, tIdx=2
     * @oracle The exception IndexOutOfBoundsException rises.
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
     * @input list[1,2,3,4,5] fIdx=4, tIdx=2
     * @oracle The exception IndexOutOfBoundsException rises.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveRangeWithIndexFault(){
        PhonyList<Integer> l=new PhonyList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);
        int index=4;
        l.removeRange(index, 2);


    }

    /**
     * Some tests for removeRange(int.int). This test is for prove that if we use the same indexes for the parameters.
     * It will not change anything in the list.
     * @passed Yes
     * @type Functional
     * @see system.PhonyList#removeRange(int, int)
     * @input list[1,2,3] fidx=2,tidx=2
     * @oracle Nothing will happen.
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
