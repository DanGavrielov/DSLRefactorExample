package ext_lib.job;

public class Job {
    private final String companyName;
    private final String title;

    private Job(String companyName, String title) {
        this.companyName = companyName;
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Job{" +
                "companyName='" + companyName + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public static class Builder {
        private String companyName;
        private String title;

        public Builder setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Job build() {
            return new Job(companyName, title);
        }
    }
}
