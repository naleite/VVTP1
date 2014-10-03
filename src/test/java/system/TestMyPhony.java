package system;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class TestMyPhony {

    /*
     * Helper method to create lists.
     */
    private PhonyList<Integer> list(Integer... content) {
        PhonyList<Integer> list = new PhonyList<>();
        for (Integer i : content)
            list.add(i);
        return list;
    }

    /**
     * Tests the "get" method with a list.
     * @see PhonyList#get(int)
     * @type Functional
     * @passed Yes
     */
    @Test
    public void getElement(){
        PhonyList<Integer> mylist=list(1,2,3);
        assertEquals(1,(int)mylist.get(0));
        assertEquals(3,(int)mylist.get(2));
        assertEquals(2,(int)mylist.get(1));
    }

    /**
     * Test for "set".
     * @see system.PhonyList#set(int, Object)
     * @type Functional
     * @passed Yes
     */
    @Test
    public void testSet(){
        PhonyList<Object> l=new PhonyList<>();
        l.add(1);
        Object o=l.set(0,2);
        Object res=l.get(0);

        assertEquals(1,o);
        assertEquals(2,res);//a bug found because of the ++index in the code
    }

    /**
     * Test for "set".
     * @see system.PhonyList#set(int, Object)
     * @type Functional
     * @passed Yes
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetLargeIndex(){
        PhonyList<Object> l=new PhonyList<>();
        l.add(1);
        int index=9;
        Object o=l.set(index,2);
        Object res=l.get(0);

    }

    /**
     * Test for "set".
     * @see system.PhonyList#set(int, Object)
     * @type Functional
     * @passed Yes
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetNegativeIndex() {
        PhonyList<Object> l = new PhonyList<>();
        boolean res=l.add(1);
        int index = -1;
        Object o = l.set(index, 2);
        assertFalse(res);

    }

        /**
         * Tests for the "size' method with a list empty.
         * @see PhonyList#size()
         * @type Functional
         * @input list[]
         * @passed Yes
         */
    @Test
    public void sizeOfEmptyList()
    {
        PhonyList<Object> l=new PhonyList<Object>();
        int res=l.size();
        assertEquals(res,0);
    }

    /**
     * Tests for the "size' method with a list not empty.
     * @see PhonyList#size()
     * @type Functional
     * @input list[]
     * @passed Yes
     */
    @Test
    public void sizeOfList(){
        PhonyList<Object> l=new PhonyList<Object>();
        l.add(1);
        int res=l.size();
        assertEquals(res,1);
    }

    /**
     * Test for the "contains".
     * @see system.PhonyList#contains(Object)
     * @type Functional
     * @oracle If an element is added to a list, then the list must contain the added element.
     * @passed Yes
     */
    @Test
    public void testContains(){
        PhonyList<Integer> l=list(1);
        l.add(2);
        boolean res1=l.contains(1); //a bug found. The list does not contain the first element added.
        assertTrue(res1);
        boolean res2=l.contains(2);
        assertTrue(res2);
        boolean res3=l.contains(5);
        assertFalse(res3);

    }
    /**
     * Test for "remove".
     * @see system.PhonyList#remove(Object)
     * @type Functional
     * @passed
     */
    @Test
    public void removeElementNullWithOtherElement(){
        PhonyList<Object> l=new PhonyList<>();
        l.add(1);
        l.add(null);
        boolean res=l.remove(null);
        assertTrue(res);
        assertEquals(1,l.size());
        boolean res2=l.remove(null);
        assertFalse(res2);
        assertEquals(1,l.size());
    }

    /**
     * Test for "remove". ///Ill faut rediscuter un peu
     * @see system.PhonyList#remove(Object)
     * @type Functional
     * @passed NO
     */
    @Test
    public void removeElementNullWithoutOtherElement(){
        PhonyList<Object> l=new PhonyList<>();
        l.add(null);
        boolean res=l.remove(null);
        //assertFalse(res);
        //assertEquals(1,l.size());
        //assertEquals(0,l.indexOf(null));
        assertTrue(res);
        assertEquals(0,l.size());
        assertEquals(-1,l.indexOf(null));

    }

    /**
     * Test for "remove".
     * @see system.PhonyList#remove(Object)
     * @type Functional
     * @passed
     */
    @Test
    public void removeElements(){
        PhonyList<Integer> l=new PhonyList<>();
        l.add(1);
        l.add(2);
        l.add(3);

        boolean res=l.remove((Object)1);
        assertTrue(res);
        assertEquals(2,l.size());

        boolean res2=l.remove((Object) 4);
        assertFalse(res2);
        assertEquals(2,l.size());
        assertEquals(2,(Object)l.get(0));
        assertEquals(3,(Object)l.get(1));

    }

    /**
     * Test for "remove".
     * @see system.PhonyList#remove(Object)
     * @type Functional
     * @passed
     */
    @Test
    public void removeTheOnlyElements(){
        PhonyList<Integer> l=new PhonyList<>();
        l.add(1);
        boolean res=l.remove((Object) 1);
        assertTrue(res);
        assertEquals(0,l.size());
        assertEquals(-1,l.indexOf(1));
    }

    /**
     * Test for "isEmpty()"
     * @see PhonyList#isEmpty()
     * @type Functional
     * @passed Yes
     */
    @Test
    public void listIsEmpty(){
        PhonyList<Integer> l=new PhonyList<>();
        l.add(1);
        l.add(2);
        l.clear();
        boolean res=l.isEmpty();
        assertTrue(res);
        assertEquals(0,l.size());
        l.add(3);
        boolean res2=l.isEmpty();
        assertFalse(res2);

    }

    /**
     * Test for RemoveAll method.
     * @see system.PhonyList#removeAll(java.util.Collection)
     * @type Functional
     * @passed
     */
    @Test
    public void testRemoveSomeElements(){
        PhonyList<Integer> l=list(1,2,3,4,5);
        Collection<Integer> ElementToRemove=new ArrayList<>();
        ElementToRemove.add(1);
        ElementToRemove.add(4);
        ElementToRemove.add(6);
        ElementToRemove.add(5);
        boolean res=l.removeAll(ElementToRemove);
        assertFalse(l.contains(1));
        assertFalse(l.contains(4));
        assertFalse(l.contains(6));
        assertFalse(l.contains(5));// A bug found here, can not remove the last element in the list
        assertEquals(2,l.size());
        assertEquals(2,(int) l.get(0));
        assertEquals(3,(int) l.get(1));
        assertTrue(res);
        //assertTrue(l.removeAll(ElementToRemove));
    }

    /**
     * Test for RemoveAll method, if the elements to be removed are not in the list
     * @see system.PhonyList#removeAll(java.util.Collection)
     * @type Functional
     * @passed
     */
    @Test
    public void testRemoveElementNotInList(){
        PhonyList<Integer> l=list(1,2,3);
        Collection<Integer> ElementToRemove=new ArrayList<>();
        ElementToRemove.add(4);
        ElementToRemove.add(5);
        ElementToRemove.add(6);
        boolean res=l.removeAll(ElementToRemove);
        assertTrue(l.contains(1));
        assertTrue(l.contains(2));
        assertTrue(l.contains(3));
        assertEquals(3,l.size());
        assertFalse(res);

    }

    /**
     * Test for RemoveAll method, if the elements to be removed are not in the list
     * @see system.PhonyList#removeAll(java.util.Collection)
     * @type Functional
     * @passed
     */
    @Test
    public void testRemoveAllElementsInList(){
        PhonyList<Object> l=new PhonyList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        Collection<Integer> ElementToRemove=new ArrayList<>();
        ElementToRemove.add(2);
        ElementToRemove.add(2);
        boolean res=l.removeAll(ElementToRemove);
        assertTrue(l.contains(1));
        assertFalse(l.contains(2));
        assertTrue(l.contains(3));
        assertTrue(res);

    }

    /**
     * Test for RemoveAll method, if there is a exception for the c.contains().
     * @see system.PhonyList#removeAll(java.util.Collection)
     * @type Functional
     * @passed
     */
    @Test
    public void testRemoveElementFailTry(){
        PhonyList<Object> l=new PhonyList<>();
        l.add(1);
        l.add(2);
        l.add(null);
        Collection<Object> ElementToRemove=new ArrayList<>();
        ElementToRemove.add((String)"e");
        ElementToRemove.add((Integer)2);
        ElementToRemove.add((Double) 3.0);
        boolean res=l.removeAll(ElementToRemove);
        assertTrue(l.contains(1));
        assertFalse(l.contains(2));
        assertTrue(l.contains(null));
        assertEquals(2,l.size());
        assertTrue(res);


    }

    /**
     * Test for grow with a huge capacity using the method add()
     * @see system.PhonyList#grow(int)
     */
    //@Test(expected = OutOfMemoryError.class)
    public void testAddHuge(){
        PhonyList<Integer> l = new PhonyList<>();
        int max=Integer.MAX_VALUE-7;

        for(int i=0;i<max;i++){
            l.add(0);
        }
        assertEquals(0,l.indexOf(0));
        assertEquals(0,l.indexOf(6));
        assertEquals(max,l.size());
    }


    /**
     * Test for method addAll
     * @see system.PhonyList#addAll(int, java.util.Collection)
     * @type Functional
     * @passed
     */
    @Test
    public void testaddAll(){
        PhonyList<Integer> l=new PhonyList<>();
        Collection<Integer> c=new ArrayList<>();
        l.add(0);
        l.add(1);
        c.add(1);
        c.add(2);
        boolean res=l.addAll(1,c);
        assertEquals(4,l.size());
        //assertEquals((Integer)0,l.get(0));
        assertEquals((Integer)0,l.get(0));
        assertEquals((Integer)1,l.get(1));
        assertEquals((Integer)2,l.get(2));
        assertEquals((Integer)1,l.get(3));
        assertTrue(res);
    }

    /**
     * Test for method addAll
     * @see system.PhonyList#addAll(int, java.util.Collection)
     * @type Functional
     * @passed
     */
    @Test
    public void testaddAllToEmptyList(){
        PhonyList<Integer> l=new PhonyList<>();
        Collection<Integer> c=new ArrayList<>();
        c.add(1);
        c.add(2);
        boolean res=l.addAll(0,c);
        assertEquals(2,l.size());
        //assertEquals((Integer)0,l.get(0));
        assertEquals((Integer)1,l.get(0));
        assertEquals((Integer)2,l.get(1));

        assertTrue(res);
    }


    /**
     * Test for method addAll with an index out of bound
     * @see system.PhonyList#addAll(int, java.util.Collection)
     * @type Functional
     * @passed
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testaddAllLargeIndex(){
        PhonyList<Integer> l=new PhonyList<>();
        Collection<Integer> c=new ArrayList<>();
        l.add(3);
        c.add(1);
        c.add(2);
        int index=9;
        l.addAll(index,c);

    }

    /**
     * Test for method addAll with a negative index out of bound
     * @see system.PhonyList#addAll(int, java.util.Collection)
     * @type Functional
     * @passed
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testaddAllNegativeIndex(){
        PhonyList<Integer> l=new PhonyList<>();
        Collection<Integer> c=new ArrayList<>();
        c.add(1);
        c.add(2);
        int index=-1;
        l.addAll(index,c);

    }

    /**
     * Test for method addAll
     * @see system.PhonyList#addAll(int, java.util.Collection)
     * @type Functional
     * @passed
     */
    @Test
    public void testaddAllFromEmptyList(){
        PhonyList<Integer> l=new PhonyList<>();
        Collection<Integer> c=new ArrayList<>();
        l.add(1);
        l.add(2);
        boolean res=l.addAll(0,c);
        assertEquals(2,l.size());
        //assertEquals((Integer)0,l.get(0));
        assertEquals((Integer)1,l.get(0));
        assertEquals((Integer)2,l.get(1));

        assertFalse(res);
    }

}