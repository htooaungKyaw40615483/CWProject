---
name: 'Bug Report: Failed to Create/Update "latest" Release Tag'
about: 'When attempting to create or update a release tag with the name "latest,"
  the following error occurs:'
title: ''
labels: ''
assignees: ''

---

### Steps to Reproduce

1. Open the GitHub repository.
2. Navigate to the "Releases" section.
3. Click on the "Draft a new release" button.
4. Fill in the required fields, including "Tag version" with "latest."
5. Click on the "Publish release" button.

### Expected Behavior

Creating a new release with the tag "latest" should work without any issues, as it has been done in the past.

### Actual Behavior

Instead of creating a new tag, the error message mentioned above is displayed, and the release process falls back to updating an existing tag, which is not the intended behavior.

### Additional Information

- **Repository:** https://github.com/htooaungKyaw40615483/CWProject

- **Integration:** The issue is likely related to a third-party integration or automation script responsible for managing releases on the repository.

- **Screenshots:** 

### Reproducibility

The issue is consistently reproducible when attempting to create or update a release tag with the name "latest."

### Possible Solution

It appears that the integration or tool used for managing releases is encountering a permission issue when trying to create a new tag with the name "latest." Developers should investigate the integration's access rights and ensure it has the necessary permissions to create tags.

### Additional Context

This issue is affecting the release process and might result in outdated or incorrect information associated with the "latest" tag. Addressing this bug promptly will help maintain a smooth release workflow.

### Related Issues

None.
