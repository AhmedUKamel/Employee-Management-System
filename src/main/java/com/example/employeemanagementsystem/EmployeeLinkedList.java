package com.example.employeemanagementsystem;
public class EmployeeLinkedList {
    // ATTRIBUTES
    private class Node {
        private Employee value;
        private Node next;
        public Node(Employee employee) {
            this.value = employee;
        }
    }
    private Node head;
    private Node sorted;
    private int filled;
    public void setHead(Node head) {
        this.head = head;
    }
    public Node getHead() {
        return head;
    }
    public Node getMiddleNode(Node start, Node last) {
        if (start == null) return null;
        Node slow = start;
        Node fast = start.next;
        while (fast != last) {
            fast = fast.next;
            if (fast != last) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }
    public boolean addEmployee(Employee employee) {
        try {
            if(head == null) head = new Node(employee);
            else {
                Node pointer = head;
                while(pointer.next != null)
                    pointer = pointer.next;
                pointer.next = new Node(employee);
            }
            filled++;
            return true;
        } catch (Exception e) {
            System.out.println("ERROR ADDING EMPLOYEE");
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean delEmployee(Employee employee) {
        try {
            if(head.value.equals(employee)) {
                head = head.next;
                filled--;
                return true;
            } else {
                Node previous = head, pointer = head.next;
                while(pointer != null) {
                    if(pointer.value.equals(employee)) {
                        previous.next = pointer.next;
                        filled--;
                        return true;
                    }
                    pointer = pointer.next;
                    previous = previous.next;
                }
            } return false;
        } catch (Exception e) {
            System.out.println("ERROR DELETING EMPLOYEE");
            System.out.println(e.getMessage());
            return false;
        }
    }
    public Employee get(int index) {
        try {
            Node pointer = head;
            for(int i = 0; i < index; i++)
                pointer = pointer.next;
            return pointer.value;
        } catch (Exception e) {
            System.out.println("ERROR GETTING EMPLOYEE");
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Employee linearSearch(String target) {
        try {
            Long id = Long.parseLong(target);
            Node pointer = head;
            while(pointer != null)
                if(pointer.value.getId().equals(id))
                    return pointer.value;
                else pointer = pointer.next;
            return null;
        } catch (Exception e) {
            Node pointer = head;
            while(pointer != null)
                if(pointer.value.getName().equals(target))
                    return pointer.value;
                else pointer = pointer.next;
            return null;
        }
    }
    public Employee binarySearch(String target) {
        Node start = head;
        Node last = null;
        try {
            Long id = Long.parseLong(target);
            do {
                Node mid = getMiddleNode(start, last);
                if (mid == null) return null;
                if (mid.value.getId().equals(id)) return mid.value;
                else if (mid.value.getId().compareTo(id) < 0) start = mid.next;
                else last = mid;
            } while (last == null || last != start);
            return null;
        } catch (Exception e) {
            do {
                Node mid = getMiddleNode(start, last);
                if (mid == null) return null;
                if (mid.value.getName().equals(target)) return mid.value;
                else if (mid.value.getName().compareTo(target) < 0) start = mid.next;
                else last = mid;
            } while (last == null || last != start);
        } return null;
    }
    public void bubbleSortByName() {
        for (int pass = 0; pass < filled; pass++ ) {
            Node pointer = head;
            Node next = head.next;
            for (int step = 0; step < filled - 1; step++) {
                if (pointer.value.compareTo(next.value) > 0) {
                    Employee temp = pointer.value;
                    pointer.value = next.value;
                    next.value = temp;
                }
                pointer = next;
                next = next.next;
            }
        }
    }
    public void bubbleSortById() {
        for (int pass = 0; pass < filled; pass++ ) {
            Node pointer = head;
            Node next = head.next;
            for (int step = 0; step < filled - 1; step++) {
                if (pointer.value.getId() > next.value.getId()) {
                    Employee temp = pointer.value;
                    pointer.value = next.value;
                    next.value = temp;
                }
                pointer = next;
                next = next.next;
            }
        }
    }
    public void selectionSortByName() {
        Node pointer = head;
        while (pointer != null) {
            Node min = pointer;
            Node next = pointer.next;
            while (next != null) {
                if (min.value.compareTo(next.value) > 0)
                    min = next;
                next = next.next;
            }
            Employee temp = pointer.value;
            pointer.value = min.value;
            min.value = temp;
            pointer = pointer.next;
        }
    }
    public void selectionSortById() {
        Node pointer = head;
        while (pointer != null) {
            Node min = pointer;
            Node next = pointer.next;
            while (next != null) {
                if (min.value.getId() > next.value.getId())
                    min = next;
                next = next.next;
            }
            Employee temp = pointer.value;
            pointer.value = min.value;
            min.value = temp;
            pointer = pointer.next;
        }
    }
    public void insertionSortByName() {
        sorted = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            sortedInsertByName(current);
            current = next;
        }
        head = sorted;
    }
    public void sortedInsertByName(Node new_node) {
        if (sorted == null || sorted.value.compareTo(new_node.value) > 0) {
            new_node.next = sorted;
            sorted = new_node;
        } else {
            Node current = sorted;
            while (current.next != null && new_node.value.compareTo(current.next.value) > 0)
                current = current.next;
            new_node.next = current.next;
            current.next = new_node;
        }
    }
    public void insertionSortById() {
        sorted = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            sortedInsertById(current);
            current = next;
        }
        head = sorted;
    }
    public void sortedInsertById(Node new_node) {
        if (sorted == null || sorted.value.getId() > new_node.value.getId()) {
            new_node.next = sorted;
            sorted = new_node;
        } else {
            Node current = sorted;
            while (current.next != null && new_node.value.getId() > current.next.value.getId())
                current = current.next;
            new_node.next = current.next;
            current.next = new_node;
        }
    }
    public Node mergeSortByName(Node head) {
        if (head == null || head.next == null) return head;
        Node middle = getMiddleNode(head, null);
        Node nextofmiddle = middle.next;
        middle.next = null;
        Node left = mergeSortByName(head);
        Node right = mergeSortByName(nextofmiddle);
        Node sortedlist = sortedMergeByName(left, right);
        return sortedlist;
    }
    public Node sortedMergeByName(Node a, Node b) {
        Node result = null;
        if (a == null) return b;
        if (b == null) return a;
        if (b.value.compareTo(a.value) > 0) {
            result = a;
            result.next = sortedMergeByName(a.next, b);
        }  else {
            result = b;
            result.next = sortedMergeByName(a, b.next);
        } return result;
    }
    public  Node mergeSortById(Node head) {
        if (head == null || head.next == null) return head;
        Node middle = getMiddleNode(head, null);
        Node nextofmiddle = middle.next;
        middle.next = null;
        Node left = mergeSortById(head);
        Node right = mergeSortById(nextofmiddle);
        Node sortedlist = sortedMergeById(left, right);
        return sortedlist;
    }
    public Node sortedMergeById(Node a, Node b) {
        Node result = null;
        if (a == null) return b;
        if (b == null) return a;
        if (a.value.getId() < b.value.getId()) {
            result = a;
            result.next = sortedMergeById(a.next, b);
        } else {
            result = b;
            result.next = sortedMergeById(a, b.next);
        } return result;
    }
    public void heapSortByName() {
        Node pointer = head;
        int i = 0;
        Node[] arr = new Node[filled];
        while (pointer != null) {
            arr[i++] = pointer;
            pointer = pointer.next;
        } sortArrayByName(arr);
    }
    public void sortArrayByName(Node[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapifyByName(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            Employee temp = arr[0].value;
            arr[0].value = arr[i].value;
            arr[i].value = temp;
            heapifyByName(arr, i, 0);
        }
    }
    private void heapifyByName(Node[] arr, int n, int i) {
        int largest = i;
        int right = 2 * i + 2;
        int left = 2 * i + 1;
        if (left < n && arr[left].value.compareTo(arr[largest].value) > 0) largest = left;
        if (right < n && arr[right].value.compareTo(arr[largest].value) > 0) largest = right;
        if (largest != i) {
            Employee swap = arr[i].value;
            arr[i].value = arr[largest].value;
            arr[largest].value = swap;
            heapifyByName(arr, n, largest);
        }
    }
    public void heapSortById() {
        Node pointer = head;
        int i = 0;
        Node[] arr = new Node[filled];
        while (pointer != null) {
            arr[i++] = pointer;
            pointer = pointer.next;
        } sortArrayById(arr);
    }
    public  void sortArrayById(Node[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapifyById(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            Employee temp = arr[0].value;
            arr[0].value = arr[i].value;
            arr[i].value = temp;
            heapifyById(arr, i, 0);
        }
    }
    public  void heapifyById(Node[] arr, int n, int i) {
        int largest = i;
        int right = 2 * i + 2;
        int left = 2 * i + 1;
        if (left < n && arr[left].value.getId() > arr[largest].value.getId()) largest = left;
        if (right < n && arr[right].value.getId() > arr[largest].value.getId()) largest = right;
        if (largest != i) {
            Employee swap = arr[i].value;
            arr[i].value = arr[largest].value;
            arr[largest].value = swap;
            heapifyById(arr, n, largest);
        }
    }
    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        } head = prev;
    }
    public void clear() {
        head = null;
        filled = 0;
    }
    public int length() {
        return filled;
    }
}