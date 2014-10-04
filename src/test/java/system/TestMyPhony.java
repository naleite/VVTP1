package system;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * My own tests.
 * Created by Leite NA on 28 Sept 2014.
 * This is test class for PhonyList.
 * @author NA Leite
 * @since 28 Sept 2014
 *
 */
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
     * Test the "get" method with a list with an invalid large index.
     * @see PhonyList#get(int)
     * @type Functional
     * @input list[1,2,3] index=9
     * @oracle The exception IndexOutOfBoundsException must rise.
     * @passed Yes
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getElementIndexInvalidlarge(){
        PhonyList<Integer> mylist=list(1,2,3);
        Integer i=mylist.get(9);

    }

    /**
     * Test the "get" method with a list with an invalid negative index.
     * @see PhonyList#get(int)
     * @type Functional
     * @input list[1,2,3] index=-1
     * @oracle The exception IndexOutOfBoundsException must rise.
     * @passed Yes
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getElementIndexInvalidNeg(){
        PhonyList<Integer> mylist=list(1,2,3);
        Integer i=mylist.get(-1);

    }

    /**
     * Test the "get" method with a list.
     * @see PhonyList#get(int)
     * @type Functional
     * @input list[1,2,3]
     * @oracle The element got must be in the right place.
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
     * Test for "set" method.
     * @see system.PhonyList#set(int, Object)
     * @type Functional
     * @input list[1] index=0 o=2
     * @oracle The result of set must be the old value and the new value must be got in the new list.
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
     * Test for "set". This is a test if an index is not valid (larger than size).
     * @see system.PhonyList#set(int, Object)
     * @type Functional
     * @input list[1] index=9
     * @oracle The exception IndexOutOfBoundsException must rise.
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
     * Test for "set".This is a test if an index is not valid .
     * @see system.PhonyList#set(int, Object)
     * @type Functional
     * @input list[1] index=-1
     * @oracle The exception IndexOutOfBoundsException must rise.
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
         * @oracle The size of an empty list must be zero.
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
     * @oracle The size of a list must equal to the number of elements in the list.
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
     * Test for the method "contains".
     * @see system.PhonyList#contains(Object)
     * @type Functional
     * @input list[1,2]
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
     * Test for "remove". This is test which for removing null from a not empty list.
     * @see system.PhonyList#remove(Object)
     * @type Functional
     * @input list[1,null] obj=null
     * @oracle Can remove "null" normally.
     * @passed Yes
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
     * Test for "remove". Remove some elements in or not in the list.
     * @see system.PhonyList#remove(Object)
     * @type Functional
     * @input list[1,2,3] o1=1 o2=4
     * @oracle The element contained in the list must be removed.
     * @passed Yes
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
     * Test for "remove". To remove the only-element list.
     * @see system.PhonyList#remove(Object)
     * @type Functional
     * @input list[1] o=1
     * @oracle The new list must be empty.
     * @passed Yes
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
     * Test for "isEmpty()".
     * @see PhonyList#isEmpty()
     * @type Functional
     * @input list[1,2] function clear()
     * @oracle The list after clear must be empty.
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
     * Test for RemoveAll method. To remove a collection of elements from an exist list.
     * @see system.PhonyList#removeAll(java.util.Collection)
     * @type Functional
     * @input list[1,2,3,4,5] collection[1,4,6,5]
     * @oracle The new list must not contain the element which we want to remove.
     * @passed Yes
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
     * Test for RemoveAll method. To remove a collection of elements from an exist list.
     * @see system.PhonyList#removeAll(java.util.Collection)
     * @type Functional
     * @input list[1,2,3,null,4,5,1,4] collection[null,5,4,6,1]
     * @oracle The new list must not contain the element which we want to remove.
     * @passed Yes
     */
    @Test
    public void testRemoveSomeSameElements(){
        PhonyList<Integer> l=list(1,2,3,null,4,5,1,4);
        Collection<Integer> ElementToRemove=new ArrayList<>();
        ElementToRemove.add(null);
        ElementToRemove.add(5);
        ElementToRemove.add(4);
        ElementToRemove.add(6);
        ElementToRemove.add(1);
        boolean res=l.removeAll(ElementToRemove);
        assertFalse(l.contains(1));
        assertFalse(l.contains(4));
        assertFalse(l.contains(6));
        assertFalse(l.contains(5));
        assertEquals(2,l.size());
        assertEquals(2,(int) l.get(0));
        assertEquals(3,(int) l.get(1));
        assertTrue(res);

    }


    /**
     * Test for RemoveAll method, if there is a exception for the c.contains().
     * @see system.PhonyList#removeAll(java.util.Collection)
     * @type Functional
     * @input list[1,2,null] clt[String "e", Integer 2, Double 3.0]
     * @oracle No exception rises and the right element can be rightly removed.
     * @passed Yes
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
     * Test for method addAll. Add a collection to a list with valid index.
     * @see system.PhonyList#addAll(int, java.util.Collection)
     * @type Functional
     * @input list[0,1,2] clt[1,2]
     * @oracle The collection can be added into the right place.
     * @passed Yes
     */
    @Test
    public void testaddAll(){
        PhonyList<Integer> l=new PhonyList<>();
        Collection<Integer> c=new ArrayList<>();
        l.add(0);
        l.add(1);
        l.add(2);
        c.add(1);
        c.add(2);
        boolean res=l.addAll(1,c);
        assertEquals(5,l.size());
        //assertEquals((Integer)0,l.get(0));
        assertEquals((Integer)0,l.get(0));
        assertEquals((Integer)1,l.get(1));
        assertEquals((Integer)2,l.get(2));
        assertEquals((Integer)1,l.get(3));
        assertEquals((Integer)2,l.get(4));
        assertTrue(res);
    }

    /**
     * Test for method addAll. Add collection to a empty list.
     * @see system.PhonyList#addAll(int, java.util.Collection)
     * @type Functional
     * @input list[] clt[1,2]
     * @oracle Can be normally added.
     * @passed Yes
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
     * @input index=9
     * @oracle The exception IndexOutOfBoundsException rises.
     * @passed Yes
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
     * @input index=-1
     * @oracle The exception IndexOutOfBoundsException rises.
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
     * Test for method addAll. Add an empty collection to a list.
     * @see system.PhonyList#addAll(int, java.util.Collection)
     * @type Functional
     * @input list[1,2] clt[]
     * @oracle The new list is same as the old one.
     * @passed Yes
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