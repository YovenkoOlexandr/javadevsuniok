import java.util.*;


public class Starter {

    private static final List<String> suffixes = Arrays.asList("@service1.com", "@service2.com", "@service3.com",
            "@service4.com", "@service5.com", "@service6.com", "@service7.com", "@service8.com", "@service9.com",
            "@service10.com", "@service11.com", "@service12.com", "@service13.com", "@service14.com", "@service15.com",
            "@service16.com", "@service17.com", "@service18.com", "@service19.com", "@service20.com", "@service21.com",
            "@service22.com"); //constants for endings
    private static final Random random = new Random();
    private static final int MAX_EMAILS_COUNT = 100000; //change it, if you want more or less emails for testing
    private static final Map<String, Integer> emailDomainsWithCounterMap = new HashMap<>(); //used as storage for all generated emails
    private static Set<Pair> top10EmailDomainsSortedSet = new TreeSet<Pair>(new Comparator<Pair>() {
        @Override
        public int compare(Pair o1, Pair o2) {
            return o2.getCounter() - o1.getCounter();
        }
    }); //used as cache for top 10 popular domains

    public static void main(String[] args) {
        for (int i = 0; i < MAX_EMAILS_COUNT; i++) {
            final String email = generateEmail();
            final String [] emailChunks = email.split("@"); //we need second chunk with domain name

            if (emailDomainsWithCounterMap.containsKey(emailChunks[1])) {
                emailDomainsWithCounterMap.put(emailChunks[1], emailDomainsWithCounterMap.get(emailChunks[1]) + 1);
            } else {
                emailDomainsWithCounterMap.put(emailChunks[1], 1);
            }
            final Pair pairForInsert = new Pair(emailChunks[1], emailDomainsWithCounterMap.get(emailChunks[1]));
            final Pair pairForRemove = new Pair(emailChunks[1], emailDomainsWithCounterMap.get(emailChunks[1]) - 1);
            top10EmailDomainsSortedSet.remove(pairForRemove);
            top10EmailDomainsSortedSet.add(pairForInsert);
        }

        top10EmailDomainsSortedSet.stream().limit(10).forEach(System.out::println);

        System.out.println("done");
    }

    private static String generateEmailPrefix() {
        final int lowBoundary = 97; // start with letter 'a'
        final int upperBoundary = 122; // finish with letter 'z'
        final int length = 10;
        final Random random = new Random();
        final StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = lowBoundary + (int)
                    (random.nextFloat() * (upperBoundary - lowBoundary + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    private static int getRandomEmailSuffixNumber(int min, int max) {
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    private static String generateEmail() {
        return generateEmailPrefix() + suffixes.get(getRandomEmailSuffixNumber(1, suffixes.size()));
    }
}