public class XMLValidator {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Invalid");
            return;
        }

        String xml = args[0];
        if (isValidXML(xml)) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
    }

    private static boolean isValidXML(String xml) {
        java.util.Stack<String> stack = new java.util.Stack<>();
        int i = 0;
        while (i < xml.length()) {
            if (xml.charAt(i) == '<') {
                int j = xml.indexOf('>', i);
                if (j == -1) return false;

                String tag = xml.substring(i + 1, j);

                if (tag.length() == 0) return false;

                boolean isClosing = tag.startsWith("/");
                String tagName = isClosing ? tag.substring(1) : tag;

                if (isClosing) {
                    if (stack.isEmpty() || !stack.peek().equals(tagName)) {
                        return false;
                    }
                    stack.pop();
                } else {
                    // Opening tag: must not contain space or '='
                    if (tag.contains(" ") || tag.contains("=")) return false;
                    stack.push(tagName);
                }

                i = j + 1;
            } else {
                i++;
            }
        }

        return stack.isEmpty();
    }
}
