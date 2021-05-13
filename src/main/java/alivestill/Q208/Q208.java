package alivestill.Q208;

public class Q208 {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }
}

/// @brief trie tree node with no path compact
class Trie {

    class TrieNode {
        boolean terminator = false;
        TrieNode[] nodes = new TrieNode[26];
        TrieNode() {
        }
        TrieNode getChild(int index) {
            return nodes[index];
        }
        void setChild(int index, TrieNode trieNode) {
            nodes[index] = trieNode;
        }

        public boolean isTerminator() {
            return terminator;
        }

        public void setTerminator(boolean terminator) {
            this.terminator = terminator;
        }
    }

    TrieNode sentinel = new TrieNode();

    /** Initialize your data structure here. */
    public Trie() {

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        int pos = 0;
        TrieNode node = sentinel;
        while (pos != word.length() && null != node.getChild(word.charAt(pos) - 'a')) {
            node = node.getChild(word.charAt(pos) - 'a');
            ++ pos;
        }
        // split up new path
        if (pos != word.length()) {
            while (pos != word.length()) {
                TrieNode newNode = new TrieNode();
                node.setChild(word.charAt(pos) - 'a', newNode);
                node = newNode;
                ++ pos;
            }
        }
        if (node != null) {
            node.setTerminator(true);
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode trieNode = getLastNode(word);
        return trieNode != null && trieNode.isTerminator();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return getLastNode(prefix) != null;
    }

    TrieNode getLastNode(String prefix) {
        TrieNode trieNode = sentinel;
        for (int i = 0; i < prefix.length(); ++ i) {
            if (trieNode.getChild(prefix.charAt(i) - 'a') == null) {
                return null;
            }
            trieNode = trieNode.getChild(prefix.charAt(i) - 'a');
        }
        return trieNode;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
