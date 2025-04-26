/*
 * @source https://leetcode.com/problems/implement-trie-ii-prefix-tree/
 * @author xiaoque
 * @date 2025.04.22
 */
public class ImplementTrie {
    public class Trie {
        private int leafCnt;
        private int prefixCnt;
        private Trie[] children;

        public Trie() {
            leafCnt = 0;
            prefixCnt = 0;
            children = new Trie[26];
        }

        public void insert(String word) {
            Trie curr = this;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (curr.children[index] == null)
                    curr.children[index] = new Trie();
                curr = curr.children[c];
                curr.prefixCnt++;
            }
            curr.leafCnt++;
        }

        public int countWordsEqualTo(String word) {
            Trie node = search(word);
            return node == null ? 0 : node.leafCnt;
        }

        public int countWordsStartingWith(String word) {
            Trie node = search(word);
            return node == null ? 0 : node.prefixCnt;
        }

        public void erase(String word) {
            Trie curr = this;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                curr = curr.children[index];
                curr.prefixCnt--;
            }
            curr.leafCnt--;
        }

        private Trie search(String word) {
            Trie curr = this;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (curr.children[index] == null)
                    return null;
                curr = curr.children[index];
            }
            return curr;
        }

    }

}
